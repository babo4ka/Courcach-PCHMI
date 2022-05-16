package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./EnterScene/EnterScene.fxml"));
        primaryStage.setTitle("Поликлиника");
        primaryStage.setScene(new Scene(root, 540, 400));
        primaryStage.show();

        User.loadUsers();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
