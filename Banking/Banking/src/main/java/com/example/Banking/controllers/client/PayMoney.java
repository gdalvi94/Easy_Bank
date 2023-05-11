package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.view.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PayMoney implements Initializable {
    
    public Text zell_username_lbl;
    public Text amt_lbl;
    public TextField zelle_field_username;
    public TextField zell_amt_field;
    public TextField zelle_msg_field;
    public Button zelle_send_button;
    public Label Error_lbl_Money_Transfer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zelle_send_button.setOnAction(event -> onSendMoney());
    }
    private void onSendMoney() {
        String receiver = zelle_field_username.getText();
        String message = zelle_msg_field.getText();
        if(receiver.equals("") || zell_amt_field.getText().equals("") || message.equals("")){
            Error_lbl_Money_Transfer.setText("All fields are required");
        }
        else {
            double amount = Double.parseDouble(zell_amt_field.getText());
            String sender = Model.getInstance().getClientData().usernameProperty().get();
            ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchUser(receiver);
            try {
                if (resultSet.isBeforeFirst()) {
                    Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Subtract amount sent from sender's account
            Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");

            //Update receiver's account balance after receiving money
            Model.getInstance().getClientData().savingAccProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsBalance(sender));
            //Record new Transaction
            Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);

            //Clearing the fields
            zelle_field_username.setText("");
            zell_amt_field.setText("");
            zelle_msg_field.setText("");
            Error_lbl_Money_Transfer.setText("");
        }
    }
}
