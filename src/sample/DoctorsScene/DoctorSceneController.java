package sample.DoctorsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorSceneController {

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
    private void open_patients_scene(ActionEvent e) throws IOException {
        Stage patientsState = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../PatientsScene/PatientsScene.fxml"));
        patientsState.setTitle("Поликлиника");
        patientsState.setScene(new Scene(root, 700, 500));
        patientsState.show();

        ((Stage)add_doc_btn.getScene().getWindow()).close();

    }
}
