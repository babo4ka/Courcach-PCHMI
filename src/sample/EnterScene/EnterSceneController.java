package sample.EnterScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.User;


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
        if(login_input.getText().equals("") || password_input.getText().equals("")){
            error_lbl.setText("Введите данные во все поля");
            error_lbl.setVisible(true);
            return;
        }

        if(User.checkUser(login_input.getText(), password_input.getText())){
            error_lbl.setText("Успешно!");
        }else{
            error_lbl.setText("Неверный логин или пароль!");
        }
        error_lbl.setVisible(true);
    }

}
