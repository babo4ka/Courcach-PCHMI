package Birzha.part2;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXListView<Security> birzhaList;

    @FXML
    private JFXListView<Security> mySecuritiesList;

    @FXML
    private Label idLbl;

    @FXML
    private JFXListView<Wallet> walletsList;

    private Account owner;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        owner = new Account();
        ObservableList<Wallet> wallets = FXCollections.observableArrayList(owner.getWallets());



        birzhaList.setCellFactory(param -> new ListCell<Security>(){
            @Override
            protected void updateItem(Security item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + ", Цена: " + item.getPrice());
                }
            }
        });

        birzhaList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    BuySecurity(birzhaList.getSelectionModel().getSelectedItem());
                }
            }
        });



        mySecuritiesList.setCellFactory(param -> new ListCell<Security>(){
            @Override
            protected void updateItem(Security item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + ", Цена: " + item.getPrice());
                }
            }
        });

        mySecuritiesList.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                SellSecurity(mySecuritiesList.getSelectionModel().getSelectedItem());
            }
        });

        walletsList.setCellFactory(param ->  new ListCell<Wallet>(){
            @Override
            protected void updateItem(Wallet item, boolean empty){
                super.updateItem(item, empty);

                if (empty || item == null || item.getID() == null) {
                    setText(null);
                } else {
                    setText(item.getID() + ", Баланс: " + item.getMoney());
                }
            }
        });



        walletsList.setOnMouseClicked(event -> {
            choosenWallet = walletsList.getSelectionModel().getSelectedItem();
        });

        walletsList.setItems(wallets);





        ObservableList<Security> securities = FXCollections.observableArrayList();
        securities.add(new Security("Жопа", 25000.0));
        securities.add(new Security("Жопа2", 25034.0));
        securities.add(new Security("Жопа3", 56430.0));
        securities.add(new Security("Жопа4", 89452.0));
        birzhaList.setItems(securities);
    }

    private Wallet choosenWallet;

    private void UpdateWallet(Wallet wallet){
        ObservableList<Wallet> wallets = walletsList.getItems();
        int i=0;
        for(Wallet w: wallets){
            if(w.getID().equals(wallet.getID()))break;
            i++;
        }

        wallets.remove(i);
        wallets.add(i, wallet);
    }

    private void SellSecurity(Security security) {
        if(choosenWallet == null){
            System.out.println("Выберите кошелёк для зачисления средств");
            return;
        }

        owner.getWalletByID(choosenWallet.getID()).addMoney(security.getPrice());
        owner.sellSecutity(security);
        UpdateLists(action.sell, security);
        System.out.println("Покупатель " + owner.getID() +
                " продал бумагу " + security.getName() +
                " за " + security.getPrice());
        UpdateWallet(choosenWallet);
    }


    //покупка
    private void BuySecurity(Security security){
        if(choosenWallet == null){
            System.out.println("Выберите кошелёк");
            return;
        }

        if(choosenWallet.getMoney() >= security.getPrice()){
            owner.getWalletByID(choosenWallet.getID()).spendMoney(security.getPrice());
            owner.addSecurity(security);
            UpdateLists(action.buy, security);
            System.out.println("Покупатель " + owner.getID() +
                    " купил бумагу " + security.getName() +
                    " за " + security.getPrice());
            UpdateWallet(choosenWallet);
            return;
        }

        System.out.println("На выбранном кошельке не хватает средств, выберите другой кошелёк");
    }

    enum action{
        buy,
        sell
    }
    private void UpdateLists(action action, Security security){
        ObservableList<Security> securities = birzhaList.getItems();
        ObservableList<Security> userSecurities = FXCollections.observableArrayList(owner.getSecurities());
        ObservableList<Security> userSecuritiesList = mySecuritiesList.getItems();
        switch (action){
            case buy:
                securities.remove(security);
                mySecuritiesList.setItems(userSecurities);
                break;

            case sell:
                securities.add(security);
                birzhaList.setItems(securities);
                userSecuritiesList.remove(security);
                break;
        }
    }
}
