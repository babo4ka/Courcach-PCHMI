package sample.DoctorsScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Doctor;

public class AddDocController {

    @FXML
    private TextField doc_name_input;
    @FXML
    private TextField doc_surname_input;

    @FXML
    private Label doc_error_adding;

    @FXML
    private Button add_doc_btn;

    @FXML
    private void add_doctor(ActionEvent e){
        if(doc_name_input.getText().equals("") || doc_surname_input.getText().equals("")){
            doc_error_adding.setText("Введите данные в пустые поля!");
            doc_error_adding.setVisible(true);
            return;
        }

        new Doctor(doc_name_input.getText(), doc_surname_input.getText());
        doc_error_adding.setText("Успешно добавлен новый доктор!");
        doc_error_adding.setVisible(true);
    }
}
