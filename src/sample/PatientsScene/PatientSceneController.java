package sample.PatientsScene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Doctor;
import sample.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientSceneController {

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
    private ListView pats_listview;

    private Patient choosedPatient;
    private List<Patient> patients = null;
    private ObservableList<Patient> patsList = FXCollections.observableArrayList();

    @FXML
    private void delete_patient(ActionEvent e){
        if(choosedPatient == null)return;

        try{
            File source = new File("./src/sample/database/patients.txt");

            BufferedReader reader = new BufferedReader(new FileReader(source));

            String line;
            String docToDelete = choosedPatient.toSaveString();
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
    private void open_doctors_scene(ActionEvent e) throws IOException {
        Stage doctorsState = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../DoctorsScene/DoctorScene.fxml"));
        doctorsState.setTitle("Поликлиника");
        doctorsState.setScene(new Scene(root, 700, 500));
        doctorsState.show();

        ((Stage)add_pat_btn.getScene().getWindow()).close();
    }
}
