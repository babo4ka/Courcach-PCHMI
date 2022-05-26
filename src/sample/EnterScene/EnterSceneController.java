package sample.EnterScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;


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
    private void enter(ActionEvent e) throws IOException {
        if(login_input.getText().equals("") || password_input.getText().equals("")){
            error_lbl.setText("Введите данные во все поля");
            error_lbl.setVisible(true);
            return;
        }

        if(User.checkUser(login_input.getText(), password_input.getText())){
            Stage doctorsState = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../DoctorsScene/DoctorScene.fxml"));
            doctorsState.setTitle("Поликлиника");
            doctorsState.setScene(new Scene(root, 700, 500));
            doctorsState.show();

            ((Stage)enter_btn.getScene().getWindow()).close();
        }else{
            error_lbl.setText("Неверный логин или пароль!");
        }
        error_lbl.setVisible(true);
    }

}
