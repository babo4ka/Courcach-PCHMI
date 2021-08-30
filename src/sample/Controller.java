package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private BubbleChart<String, String> lohchart;
    @FXML private NumberAxis xAxis, yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
