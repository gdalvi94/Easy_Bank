package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public Label amount_lbl;
    public Label reciever_lbl;
    public Label sender_lbl;

    public Label trans_date_lbl;
    public Label lbl_transc_icon;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sender_lbl.textProperty().bind(transaction.senderProperty());
        reciever_lbl.textProperty().bind(transaction.receiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_date_lbl.textProperty().bind(transaction.dateProperty().asString());
        changeTransactionSymbols();

    }

    public void changeTransactionSymbols(){
        if(transaction.senderProperty().get().equals(Model.getInstance().getClientData().usernameProperty().get())){
            lbl_transc_icon.setText("Sent");
            lbl_transc_icon.setTextFill(Color.rgb(255, 0, 0));

        }else{
            lbl_transc_icon.setText("Received");
            lbl_transc_icon.setTextFill(Color.rgb(0, 128, 0));
        }

    }
}