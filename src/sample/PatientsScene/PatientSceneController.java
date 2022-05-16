package sample.PatientsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
