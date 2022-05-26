package sample.DoctorsScene;

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
import sample.Doctor;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class DoctorSceneController implements Initializable {

    @FXML
    private MenuButton nav_menu;

    @FXML
    private MenuItem nav_patients;
    @FXML
    private MenuItem nav_doctors;

    @FXML
    private Label doc_name_lbl;
    @FXML
    private Label doc_id_lbl;

    @FXML
    private Button add_doc_btn;
    @FXML
    private Button remove_doc_btn;

    @FXML
    private TextField doc_search_field;
    @FXML
    private ListView docs_listview;

    @FXML
    private Label update_info;


    @FXML
    private void open_add_doctor(ActionEvent e) throws IOException {
        Stage dialog = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./AddDocScene.fxml"));
        Stage parentStage = (Stage)add_doc_btn.getScene().getWindow();

        dialog.setTitle("Добавить доктора");
        dialog.setScene(new Scene(root, 500, 128));

        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.showAndWait();

    }

    private Doctor choosedDoctor = null;
    private List<Doctor> doctors = new ArrayList<>();
    private ObservableList<Doctor> docsList = FXCollections.observableArrayList();

    @FXML
    private void delete_doctor(ActionEvent e){
        if(choosedDoctor == null)return;

        try{
            File source = new File("./src/sample/database/doctors.txt");

            BufferedReader reader = new BufferedReader(new FileReader(source));

            String line;
            String docToDelete = choosedDoctor.toSaveString();
            List<String> lines = new ArrayList<>();

            while((line = reader.readLine()) != null){
                if(!line.equals(docToDelete)){
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

            doctors.remove(choosedDoctor);
            docsList.remove(choosedDoctor);
            choosedDoctor = null;

            doc_name_lbl.setText("Доктор Фамилия Имя");
            doc_id_lbl.setText("ID:");

        }catch (IOException err){
            err.printStackTrace();
        }

    }

    @FXML
    private void open_patients_scene(ActionEvent e) throws IOException {
        Stage patientsState = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../PatientsScene/PatientsScene.fxml"));
        patientsState.setTitle("Поликлиника");
        patientsState.setScene(new Scene(root, 700, 550));
        patientsState.show();

        ((Stage)add_doc_btn.getScene().getWindow()).close();

    }

    //поиск докторов
    @FXML
    private void search_doctors(){
        doctors = Doctor.getDoctors();

        if(!doc_search_field.getText().equals("")){
            Stream<Doctor> result =
                    Stream.of(doctors.toArray(new Doctor[0])).filter
                            (s->s.toNameString().lastIndexOf(doc_search_field.getText().toLowerCase(Locale.ROOT)) != -1
                                    || s.toNameStringReverse().lastIndexOf(doc_search_field.getText().toLowerCase(Locale.ROOT)) != -1);
            doctors = new ArrayList<>();
            result.forEach((s->doctors.add(s)));
        }


        docsList.clear();
        docsList.addAll(doctors);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctors = Doctor.getDoctors();

        for(Doctor doc : doctors){
            docsList.add(doc);
        }
        docs_listview.setItems(docsList);

        docs_listview.setCellFactory(param -> new ListCell<Doctor>(){
            @Override
            protected void updateItem(Doctor item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null){
                    setGraphic(null);
                    return;
                }

                setGraphic(new Label(item.getName() + " " + item.getSurname()));
            }
        });

        docs_listview.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    choosedDoctor = (Doctor) newValue;
                    doc_name_lbl.setText("Доктор " + choosedDoctor.getSurname() + " " + choosedDoctor.getName());
                    doc_id_lbl.setText("ID: " + choosedDoctor.getId());
        });

    }
}
