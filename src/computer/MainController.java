package computer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainController {

    //главная панель
    @FXML private AnchorPane pane;

    @FXML
    private ToolBar toolBar;

    //кнопка для добавления продукта
    @FXML
    private JFXButton addProductObjectBtn;

    //кнопка сортировки
    @FXML
    private JFXButton sortBtn;

    //кнопка для фильтров
    @FXML
    private JFXButton filtrBtn;

    //панель, содержащая все продукты
    @FXML
    private AnchorPane contentPane;

    //кнопка добавления продукта
    @FXML
    private JFXButton addProductBtn;


    //POAW - product object addind window
    private boolean isPOAWOnScene;
    private Parent POAW;
    /**
     * достаёт окно для добавления объекта продукта
     * */
    @FXML private void TryAddProductObject() throws IOException {

        if(!isPOAWOnScene) {
            POAW = FXMLLoader.load(getClass().getResource("ProductCreatePage.fxml"));

            //добавление окна на сцену
            pane.getChildren().add(POAW);
        }
            //начальные координаты
            POAW.setTranslateX(-((AnchorPane) POAW).getPrefWidth());
            POAW.setTranslateY(18);


            //на кнопку отмены убираем окно
            ((JFXButton) POAW.lookup("#cancelBtn")).setOnAction(event -> {
                RemovePOAW(POAW);
            });

            //список полей для характеристик
            List<JFXTextField> characteristicsFields = new ArrayList<>();

            //на кнопку добавить добавляем объект продукта
            ((JFXButton) POAW.lookup("#addBtn")).setOnAction(event -> {
                String name = ((JFXTextField) POAW.lookup("#nameField")).getText();

                if( name.equals("")){
                    return;
                }


                if(characteristicsFields.size() == 0){
                    return;
                }

                List<ProductCharacteristic> characteristics = new ArrayList<>();
                for(JFXTextField field:characteristicsFields){
                    characteristics.add(new ProductCharacteristic(field.getText()));
                }

                ProductObject productObject = new ProductObject(name, characteristics);

                RemovePOAW(POAW);
            });


            //создание анимации вывода окна из-за краёв экрана
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), POAW);
            tt.setByX(((AnchorPane) POAW).getPrefWidth());
            tt.play();

            isPOAWOnScene = true;

            //добавление характеристики по нажатию на кнопку

            //счетчик id для полей
            AtomicReference<Integer> idIterator = new AtomicReference<>(0);

            JFXButton characteristicAddBtn = (JFXButton) POAW.lookup("#addCharacteristicBtn");

            //Node previousPoint = characteristicAddBtn;
            AtomicReference<Node> previousPoint = new AtomicReference<>(characteristicAddBtn);

            characteristicAddBtn.setOnAction(event -> {
                JFXTextField characteristicField = createCharacteristicField(previousPoint.get(), idIterator.get());
                characteristicsFields.add(characteristicField);

                idIterator.getAndSet(idIterator.get() + 1);

                previousPoint.getAndSet(characteristicField);

                AnchorPane characteristicsPane = ((AnchorPane) POAW.lookup("#characteristicsPane"));

                characteristicsPane.getChildren().add(characteristicField);
            });
    }

    private void RemovePOAW(Node POAW){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), POAW);

        tt.setByX(-((AnchorPane) POAW).getPrefWidth());

        tt.setOnFinished(event1 -> {
            ObservableList<Node> objs = pane.getChildren();
            objs.remove(POAW);
            isPOAWOnScene = false;
        });

        tt.play();
    }

    private JFXTextField createCharacteristicField(Node previousField, Integer idIterator){
        double width = 100;
        double height = 30;
        String id = "characteristicField_" + idIterator;

        JFXTextField characteristicField = new JFXTextField();
        characteristicField.setId(id);

        double layoutX, layoutY;

        if(idIterator == 0 || idIterator % 3 != 0){
            layoutY = previousField.getLayoutY();
            if(idIterator == 0){
                layoutX = previousField.getLayoutX()+ 50;
            }else{
                layoutX = previousField.getLayoutX()+ 120;
            }
        }else{
            layoutY = previousField.getLayoutY() + 30;
            layoutX = previousField.getLayoutX() - 240;
        }

        characteristicField.setPrefSize(width, height);

        characteristicField.setLayoutX(layoutX);
        characteristicField.setLayoutY(layoutY);

        return characteristicField;
    }


    //PAW - product adding window
    private boolean isPAWOnScene;
    private Parent PAW;
    /**
     * достаёт окно для добавления продукта
     * */
    @FXML private void TryAddProduct() throws IOException {


        if(!isPAWOnScene){
            PAW = FXMLLoader.load(getClass().getResource("ProductAddPage.fxml"));
            //добавление окна на сцену
            pane.getChildren().add(PAW);
        }

        PAW.setTranslateX(pane.getScene().getWidth());
        PAW.setTranslateY(pane.getScene().getHeight() - ((AnchorPane)PAW).getPrefHeight());

        ((JFXButton)PAW.lookup("#cancelBtn")).setOnAction(event -> {
            RemovePAW(PAW);
        });

        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), PAW);
        //tt.setByX(((AnchorPane) POAW).getPrefWidth());
        tt.setByX(pane.getScene().getWidth() - ((AnchorPane) PAW).getPrefWidth());
        tt.play();

        System.out.println(PAW.getTranslateX());

        isPAWOnScene = true;

    }

    private void RemovePAW(Node PAW){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), PAW);

        tt.setByX(pane.getWidth());


        tt.setOnFinished(event1 -> {
            ObservableList<Node> objs = pane.getChildren();
            objs.remove(PAW);
            isPAWOnScene = false;
        });

        tt.play();
    }
}
