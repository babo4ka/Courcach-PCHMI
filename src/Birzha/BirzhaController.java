package Birzha;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class BirzhaController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private LineChart<String, Number> dataChart;

    @FXML
    private CategoryAxis variableLine;

    @FXML
    private NumberAxis valuesChart;

    @FXML private JFXButton loadDataBtn;

    private Currency currencyToAnalyze;

    @FXML private JFXTextField dayChoose;

    @FXML private void AnalyzeDay(){
        XYChart.Series<String, Number> dataToAnalyze = dataChart.getData().get(0);

        int day = Integer.parseInt(dayChoose.getText());
        if(day > 30 || day < 1){
            System.out.println("Неверно введённый день");
            return;
        }

        String dayValue = dataToAnalyze.getData().get(day-1).getXValue();

        Double price = dataToAnalyze.getData().get(day-1).getYValue().doubleValue();

        System.out.println("В " + dayValue + "й день цена составляла " + String.format("%.2f", price));
    }


    @FXML private void AnalyzeData(){
        XYChart.Series<String, Number> dataToAnalyze = dataChart.getData().get(0);

        Double start = 0.0,
                last = 0.0,
                minimum = 15000000000.0,
                maximum = 0.0;

        String startDay = "1", minimumDay = "", maximumDay = "";


        int iterator = 1;
        int lastPlace = dataToAnalyze.getData().size();
        for(XYChart.Data<String, Number> data : dataToAnalyze.getData()){
            if(iterator == 1){
                start = data.getYValue().doubleValue();
            }

            if(iterator == lastPlace){
                last = data.getYValue().doubleValue();
            }

            Double value = data.getYValue().doubleValue();

            if(value > maximum){
                maximum = value;
                maximumDay = data.getXValue();
            }

            if(value < minimum){
                minimum = value;
                minimumDay = data.getXValue();
            }
            iterator++;
        }

        Double change = last - start;

        Double percChange = (Math.abs(change)/start) * 100;


        if(change > 0){
            System.out.println("Цена выросла на " + String.format("%.2f", change) + " единиц (" + String.format("%.2f", percChange) + "%)");
        }else if(last - start < 0){
            System.out.println("Цена упала на " + String.format("%.2f" , Math.abs(change)) + " единиц(" + String.format("%.2f", percChange) + "%)");
        }

        System.out.printf("%.2f%n", start);
        System.out.printf("%.2f%n", last);
        System.out.println("Минимум в " + minimumDay + " й день - " + String.format("%.2f", minimum));
        System.out.println("Максимум в " + maximumDay + " й день - " + String.format("%.2f", maximum));


    }

    private Double rand(Double min, Double max){
        max -= min;
        return (Math.random() * ++max) + min;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Currency zhopa = new Currency("Жопакоин");
        Double [] rate = new Double[30];
        for(int i=0; i<rate.length;i++){
            rate[i] = rand(10000.0, 670000.0);
        }
        zhopa.setLastMonthRate(rate);
        variableLine.setLabel(zhopa.getCurrencyName());


        XYChart.Series<String, Number> series = new XYChart.Series<>();

        int day = 1;
        for(Double d:rate){
            series.getData().add(new XYChart.Data<>(Integer.toString(day), d));
            day++;
        }

        dataChart.getData().add(series);
    }
}
