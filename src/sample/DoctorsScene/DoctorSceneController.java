package sample.DoctorsScene;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Doctor;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    @FXML
    private void delete_doctor(ActionEvent e){
        if(choosedDoctor == null)return;
        try{
            File source = new File("./src/sample/database/doctors.txt");
            File output = new File("./src/sample/database/doctors2.txt");

            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            String line = null;
            String docToDelete = choosedDoctor.toSaveString();

            while((line = reader.readLine()) != null){
                if(!line.equals(docToDelete)){
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            source.delete();
            output.renameTo(source);
        }catch (IOException err){
            err.printStackTrace();
        }

    }


    @FXML
    private void open_patients_scene(ActionEvent e) throws IOException {
        Stage patientsState = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../PatientsScene/PatientsScene.fxml"));
        patientsState.setTitle("Поликлиника");
        patientsState.setScene(new Scene(root, 700, 500));
        patientsState.show();

        ((Stage)add_doc_btn.getScene().getWindow()).close();

    }

    private List<Doctor> doctors = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctors = Doctor.getDoctors();

        ObservableList<Doctor> docsList = FXCollections.observableArrayList();
        for(Doctor doc : doctors){
            docsList.add(doc);
        }
        docs_listview.setItems(docsList);

        docs_listview.setCellFactory(param -> new ListCell<Doctor>(){
            @Override
            protected void updateItem(Doctor item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null)return;

                setGraphic(new Label(item.getName() + " " + item.getSurname()));
            }
        });


    }
}
