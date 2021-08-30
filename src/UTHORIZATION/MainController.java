package UTHORIZATION;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXTextField lgn;

    @FXML
    private JFXTextField pwd;

    @FXML
    private JFXButton log;

    @FXML
    private JFXCheckBox rmbr;


    @FXML private void doLogin() throws IOException {
        if(!lgn.getText().equals("") && !pwd.getText().equals("")){
            if(rmbr.isSelected())remember();
            else System.out.println("net)0");

            Parent root = FXMLLoader.load(getClass().getResource("../sample/yaloh.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }else System.out.println("Введите данные!");
    }

    private void remember() throws IOException {
        File file = new File("data.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write(lgn.getText() + "\n" + pwd.getText());
        writer.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("data.txt");
        if(file.exists()){
            try {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                lgn.setText(br.readLine());
                pwd.setText(br.readLine());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
