package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./PatientsScene/PatientsScene.fxml"));
        primaryStage.setTitle("Поликлиника");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();
        Patient pa = new Patient("Женя", "Иванов", new Date());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
