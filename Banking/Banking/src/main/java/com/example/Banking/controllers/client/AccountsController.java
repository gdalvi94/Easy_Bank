package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.models.SavingAcc;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Text saving_acc_lfield;
    public Text saving_withdrawal_field;
    public Text savings_balance_field;
    public Text savings_data_field;
    public TextField savings_transfer_field;
    public Button saving_trans_btn;
    public Label check_acc_num_field;
    public Label check_tarns_limit;
    public Text checkings_acc_number_ip;
    public Text checkings_transact_limit_field;
    public Text checkings_balance_field;
    public Text checkings_date_field;
    public TextField checkings_transfer_field_box;
    public Button checkings_trans_btn;
    public Label Error_Transfer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transfer_within_bank();
//        initTransferMoney();
        saving_trans_btn.setOnAction(event -> savingMoneyTransfer());
        checkings_trans_btn.setOnAction(event -> checkingMoneyTransfer());

    }
    public void transfer_within_bank(){
        saving_acc_lfield.textProperty().bind(Model.getInstance().getClientData().savingAccProperty().get().accountNoProperty());
        checkings_acc_number_ip.textProperty().bind(Model.getInstance().getClientData().checkingAccProperty().get().accountNoProperty());
//        saving_withdrawal_field.textProperty().bind();
        savings_balance_field.textProperty().bind(Model.getInstance().getClientData().savingAccProperty().get().balanceProperty().asString());
//        savings_data_field.textProperty().bind(LocalDate.now());
        savings_data_field.setText(LocalDate.now().toString());
        checkings_balance_field.textProperty().bind(Model.getInstance().getClientData().checkingAccProperty().get().balanceProperty().asString());
        checkings_date_field.setText(LocalDate.now().toString());
    }

    // Transfer from saving to checking
    public void savingMoneyTransfer(){
        if(savings_transfer_field.getText().equals("")){
            Error_Transfer.setText("Enter valid amount");
        }
        else {
            Double amount = Double.parseDouble(savings_transfer_field.getText());
            String user = Model.getInstance().getClientData().usernameProperty().get();
            ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchUser(user);
            if (amount <= Double.parseDouble(savings_balance_field.getText())) {
                try {
                    if (resultSet.isBeforeFirst()) {
                        Model.getInstance().getDatabaseDriver().updateBalanceChecking(user, amount, "ADD");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Subtract amount sent from sender's account
                Model.getInstance().getDatabaseDriver().updateBalance(user, amount, "SUB");

                //Update receiver's account balance after receiving money
                Model.getInstance().getClientData().savingAccProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsBalance(user));
                Model.getInstance().getClientData().checkingAccProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getCheckingBalance(user));
                //Record new Transaction
                Model.getInstance().getDatabaseDriver().newTransaction(user, user, amount, "Transfer from Saving to checking account");
                savings_transfer_field.setText("");
                Error_Transfer.setText("");


            } else {
                Error_Transfer.setText("Transaction not completed due to insufficient funds");
                savings_transfer_field.setText("");
            }
        }

    }

    // Transfer from checking to saving
    public void checkingMoneyTransfer() {

        if (checkings_transfer_field_box.getText().equals("")) {
            Error_Transfer.setText("Enter valid amount");
        } else {
            Double amount = Double.parseDouble(checkings_transfer_field_box.getText());
            String user = Model.getInstance().getClientData().usernameProperty().get();
            ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchUser(user);
            if (amount <= Double.parseDouble(checkings_balance_field.getText())) {

                try {
                    if (resultSet.isBeforeFirst()) {
                        Model.getInstance().getDatabaseDriver().updateBalance(user, amount, "ADD");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Subtract amount sent from sender's account
                Model.getInstance().getDatabaseDriver().updateBalanceChecking(user, amount, "SUB");

                //Update receiver's account balance after receiving money
                Model.getInstance().getClientData().savingAccProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsBalance(user));
                Model.getInstance().getClientData().checkingAccProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getCheckingBalance(user));
                //Record new Transaction
                Model.getInstance().getDatabaseDriver().newTransaction(user, user, amount, "Transfer from Checking to Saving account");
                checkings_transfer_field_box.setText("");
                Error_Transfer.setText("");
            } else {
                Error_Transfer.setText("Transaction not completed due to insufficient funds");
                checkings_transfer_field_box.setText("");
            }
        }
    }

}
