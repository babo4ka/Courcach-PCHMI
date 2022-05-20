package sample.PatientsScene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Districts;
import sample.Patient;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class PatientSceneController implements Initializable {

    @FXML
    private MenuButton nav_menu;

    @FXML
    private MenuItem nav_patients;
    @FXML
    private MenuItem nav_doctors;

    @FXML
    private Label pat_name_lbl;
    @FXML
    private Label pat_id_lbl;

    @FXML
    private Button add_pat_btn;
    @FXML
    private Button remove_pat_btn;
    @FXML
    private Button add_rec_btn;

    @FXML
    private TextField pat_search_field;
    @FXML
    private ComboBox dist_to_srch_input;

    @FXML
    private DatePicker start_bd_search;
    @FXML
    private DatePicker end_bd_search;

    @FXML
    private ListView pats_listview;
    @FXML
    private ListView recs_listview;

    @FXML private void open_add_patient(ActionEvent e) throws IOException {
        Stage dialog = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./AddPatScene.fxml"));
        Stage parentStage = (Stage)add_pat_btn.getScene().getWindow();

        dialog.setTitle("Добавить Пациента");
        dialog.setScene(new Scene(root, 500, 177));

        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.showAndWait();
    }

    private Patient choosedPatient;
    private List<Patient> patients = new ArrayList<>();
    private ObservableList<Patient> patsList = FXCollections.observableArrayList();

    private List<String> recieps = new ArrayList<>();
    private ObservableList<String> recsList = FXCollections.observableArrayList();

    @FXML
    private void delete_patient(ActionEvent e){
        if(choosedPatient == null)return;

        try{
            File source = new File("./src/sample/database/patients.txt");

            BufferedReader reader = new BufferedReader(new FileReader(source));

            String line;
            String patToDelete = choosedPatient.toSaveString();

            List<String> lines = new ArrayList<>();

            while((line = reader.readLine()) != null){
                if(!line.equals(patToDelete)){
                    lines.add(line);
                }
            }

            BufferedWriter fake = new BufferedWriter(new FileWriter(source, false));
            fake.write("");
            fake.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(source, true));

            for(String l:lines){
                System.out.println(l);
                writer.write(l);
                writer.newLine();
            }

            reader.close();
            writer.close();

            patients.remove(choosedPatient);
            patsList.remove(choosedPatient);
            choosedPatient = null;

            pat_name_lbl.setText("Пациент Фамилия Имя");
            pat_id_lbl.setText("ID:");

        }catch (IOException err){
            err.printStackTrace();
        }

    }

    @FXML
    private void add_reciepe(ActionEvent e){
        if(choosedPatient == null)return;

        choosedPatient.addReciepe(new Date());
    }

    @FXML
    private void open_doctors_scene(ActionEvent e) throws IOException {
        Stage doctorsState = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../DoctorsScene/DoctorScene.fxml"));
        doctorsState.setTitle("Поликлиника");
        doctorsState.setScene(new Scene(root, 700, 500));
        doctorsState.show();

        ((Stage)add_pat_btn.getScene().getWindow()).close();
    }

    @FXML
    private void search_patients(){
        if(dist_to_srch_input.getValue() != null){
            patients = Patient.getPatients();
            Stream<Patient> result = Stream.of(patients.toArray(new Patient[0])).filter(s->s.getDistrict().equals(Districts.fromString(dist_to_srch_input.getValue().toString())));
            patients = new ArrayList<>();
            result.forEach(s->patients.add(s));
        }

        patsList.clear();
        patsList.addAll(patients);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patients = Patient.getPatients();

        patsList.addAll(patients);

        pats_listview.setItems(patsList);

        pats_listview.setCellFactory(param -> new ListCell<Patient>(){
            @Override
            protected void updateItem(Patient item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null){
                    setGraphic(null);
                    return;
                }

                setGraphic(new Label(item.getName() + " " + item.getSurname()));
            }
        });

        pats_listview.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    choosedPatient = (Patient) newValue;
                    pat_name_lbl.setText("Пациент " + choosedPatient.getSurname() + " " + choosedPatient.getName());
                    pat_id_lbl.setText("ID: " + choosedPatient.getId());
                    recs_listview.getItems().clear();
                    for(Date r: choosedPatient.getRecieps()){
                        recsList.add(r.toString());
                    }

                    recs_listview.setItems(recsList);
                });
        dist_to_srch_input.getItems().addAll(Districts.values());

    }
}
