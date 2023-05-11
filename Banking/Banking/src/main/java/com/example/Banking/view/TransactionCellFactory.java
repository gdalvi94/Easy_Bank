package com.example.Banking.view;

import com.example.Banking.controllers.client.TransactionCellController;
import com.example.Banking.models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

//transaction cell in transaction section on dashboard and transaction in client panel
public class TransactionCellFactory extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            TransactionCellController controller = new TransactionCellController(transaction);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}