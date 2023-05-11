package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.models.Transaction;
import com.example.Banking.view.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text acc_alb;
    public Label trans_lbl;

    public Label chk_lbl;
    public Text card1_lbl;
    public Label check_balance;
    public Label savings_lbl;
    public Text card2_lbl;
    public Label savings_balance;
    public Text summary_lbl;
    public Text income_lbl;
    public Label income_expense;
    public Text expense_lbl;
    public Label expense_miney;
    public Label username_hi_label_at_top;
    public ListView transactions_listView;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        bindData();
        initLatestTransactionsList();
        transactions_listView.setItems(Model.getInstance().getLatestTransactions());
        transactions_listView.setCellFactory(e -> new TransactionCellFactory());
        expenseCalculationSummary();


    }

    // Dashboard content
    private void bindData(){
        username_hi_label_at_top.textProperty().bind(Bindings.concat("Hey, ").concat(Model.getInstance().getClientData().fnameProperty()));
        check_balance.textProperty().bind(Model.getInstance().getClientData().checkingAccProperty().get().balanceProperty().asString());
        System.out.println("Checking Account Balance " + check_balance);
        card1_lbl.textProperty().bind(Model.getInstance().getClientData().checkingAccProperty().get().accountNoProperty());
        savings_balance.textProperty().bind(Model.getInstance().getClientData().savingAccProperty().get().balanceProperty().asString());
        System.out.println("Savings Account Balance " + savings_balance);
        card2_lbl.textProperty().bind(Model.getInstance().getClientData().savingAccProperty().get().accountNoProperty());


    }


    private void initLatestTransactionsList() {
        if (Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions();
        }

    }



    public void expenseCalculationSummary(){
        double spending=0,earning=0;
        if(Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }
        for(Transaction transaction:Model.getInstance().getAllTransactions()){
            if(transaction.senderProperty().get().equals(Model.getInstance().getClientData().usernameProperty().get())){
                spending=spending+transaction.amountProperty().get();
            }else{
                earning=earning+transaction.amountProperty().get();
            }
        }
        income_lbl.setText("+ $ "+ earning);
        expense_lbl.setText("- $" +spending);

    }

}
