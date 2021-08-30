package risovashki;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private AnchorPane main;
    @FXML private JFXButton addLinesBtn;

    boolean iscreated = false;
    private List<FractalLine> currentLines = new ArrayList<>();
    @FXML private void CreateLines(){
        System.out.println(main.getScene().getWidth());
        if(!iscreated) {
            double startX = main.getScene().getWidth() / 2, startY = main.getScene().getHeight();
            double endX = main.getScene().getWidth() / 2, endY = main.getScene().getHeight()/2;
            FractalLine line = new FractalLine(startX, startY, endX, endY, 90, startY - endY);
            main.getChildren().add(line);
            iscreated = true;
            currentLines.add(line);
        }else{
            List<FractalLine> tempLines = new ArrayList<>();

            FractalLine[] lines;
            for(FractalLine line:currentLines){
                lines = line.CreateNewLines();
                tempLines.addAll(Arrays.asList(lines));
                main.getChildren().addAll(lines);
            }
            currentLines = new ArrayList<>(tempLines);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
