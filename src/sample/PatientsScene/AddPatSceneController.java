package sample.PatientsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Districts;
import sample.Patient;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private void add_patient(ActionEvent e){
        if(pat_name_input.getText().equals("") || pat_surname_input.getText().equals("")
        || pat_bd_input.getValue() == null || pat_dist_input.getValue() == null){
            pat_error_adding.setText("Введите данные в пустые поля!");
            pat_error_adding.setVisible(true);
            return;
        }

        Date birthday = Date.from(Instant.from(pat_bd_input.getValue().atStartOfDay(ZoneId.systemDefault())));


        new Patient(pat_name_input.getText(), pat_surname_input.getText(), birthday, Districts.fromString(pat_dist_input.getValue().toString()));
//        pat_error_adding.setText("Успешно добавлен новый пациент!");
//        pat_error_adding.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pat_dist_input.getItems().addAll(Districts.values());
    }
}
