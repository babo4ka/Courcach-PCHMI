package sample.EnterScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EnterSceneController {

    //управление
    @FXML
    private TextField login_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private Button enter_btn;
    //оповещение
    @FXML
    private Label error_lbl;

    @FXML
    private void enter(ActionEvent e){
        System.out.println(!login_input.getText().equals("")&&!password_input.getText().equals("")?
                "Логин: " + login_input.getText() + ", пароль: " + password_input.getText():
                "Введите и логин и пароль");
    }

}
