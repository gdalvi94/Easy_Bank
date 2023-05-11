package com.example.Banking.controllers.admin;

import com.example.Banking.models.Client;
import com.example.Banking.models.Model;
import com.example.Banking.view.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField  username_txt;
    public Button search_btc;
    public ListView result_listview;
    public TextField amount_fld;
    public Button deposit_btn;
    public Label Error_lbl_Deposit;

    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_btc.setOnAction(event -> clientSearch());
        deposit_btn.setOnAction(event -> deposit());

    }

    //function to search client from deposits
    private void clientSearch(){
        if(username_txt.getText().equals("")){
            Error_lbl_Deposit.setText("Please enter valid username");
        }
        else {
            System.out.println("Username" + username_txt.getText());
            ObservableList<Client> res = Model.getInstance().searchUser(username_txt.getText());
            result_listview.setItems(res);
            result_listview.setCellFactory(e -> new ClientCellFactory());
            client = res.get(0);
            Error_lbl_Deposit.setText("");
        }

    }


    //function to add deposit in admin section
    private void deposit() {
        if (amount_fld.getText().equals("")) {
            Error_lbl_Deposit.setText("Please enter valid value");

        } else {
            double amt = Double.parseDouble(amount_fld.getText());
            System.out.println("amt" + amt);
            double bal = amt + client.savingAccProperty().get().balanceProperty().get();
            System.out.println("bal" + bal);
            if (amount_fld.getText() != null) {
                Model.getInstance().getDatabaseDriver().depositSavingBal(client.usernameProperty().get(), bal);
            }
            clearfields();
            Error_lbl_Deposit.setText("");
        }
    }

    //function to clear fields
    private void clearfields() {
        username_txt.setText("");
        amount_fld.setText("");
    }
}
