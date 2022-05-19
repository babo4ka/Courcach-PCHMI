package sample.PatientsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Districts;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPatSceneController implements Initializable {

    @FXML
    private TextField pat_name_input;
    @FXML
    private TextField pat_surname_input;
    @FXML
    private DatePicker pat_bd_input;
    @FXML
    private ComboBox pat_dist_input;

    @FXML
    private Label pat_error_adding;

    @FXML
    private Button pat_doc_btn;

    @FXML
    private void add_doctor(ActionEvent e){
//        if(doc_name_input.getText().equals("") || doc_surname_input.getText().equals("")){
//            doc_error_adding.setText("Введите данные в пустые поля!");
//            doc_error_adding.setVisible(true);
//            return;
//        }
//
//        new Doctor(doc_name_input.getText(), doc_surname_input.getText());
//        doc_error_adding.setText("Успешно добавлен новый доктор!");
//        doc_error_adding.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pat_dist_input.getItems().addAll(Districts.values());
    }
}
