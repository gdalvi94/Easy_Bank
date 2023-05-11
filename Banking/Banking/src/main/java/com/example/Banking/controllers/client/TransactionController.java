package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.models.Transaction;
import com.example.Banking.view.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    public Text transact_txt;
    public ListView<Transaction> transaction_list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            initAllTransactionsList();
            transaction_list.setItems(Model.getInstance().getAllTransactions());
            transaction_list.setCellFactory(e -> new TransactionCellFactory());
    }

    private void initAllTransactionsList() {
        if(Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }
    }
}
