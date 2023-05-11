package com.example.Banking.controllers.admin;

import com.example.Banking.models.Client;
import com.example.Banking.models.DatabaseDriver;
import com.example.Banking.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fName_lbl; //first name label
    public Label lName_lbl; //last name label
    public Label pAddress_lbl; //payee address label
    public Label ch_acc_lbl; //checking account number label
    public Label sv_acc_lbl; //savings account number label
    public Label date_lbl; //date label
    public Button delete_btn_lbl; //delete button label

    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fName_lbl.textProperty().bind(client.fnameProperty());
        lName_lbl.textProperty().bind(client.lnameProperty());
        pAddress_lbl.textProperty().bind(client.usernameProperty());
        ch_acc_lbl.textProperty().bind(client.checkingAccProperty().asString());
        sv_acc_lbl.textProperty().bind(client.savingAccProperty().asString());
        date_lbl.textProperty().bind(client.dateProperty().asString());


    }





}