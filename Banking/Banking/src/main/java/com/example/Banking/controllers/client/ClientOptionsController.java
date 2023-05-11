package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import com.example.Banking.view.ClientOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientOptionsController implements Initializable {
    public Button dashboard_btn;
    public Button acc_btn;
    public Button transfer_btn;

    public Button log_out_btn;
    public Button zelle_pay_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();

    }
    public void addListeners(){
        dashboard_btn.setOnAction(event -> gotoDashboard());
        transfer_btn.setOnAction(event -> gotoTransaction());
        acc_btn.setOnAction(event -> gotoAccount());
        zelle_pay_btn.setOnAction(event -> gotoPayTransfer());
        log_out_btn.setOnAction(event -> gotoLogOut());
    }
    public void gotoDashboard(){
    Model.getInstance().getView().getSelectedItem().set(ClientOptions.DASHBOARD);

    }
    public void gotoTransaction(){
        Model.getInstance().getView().getSelectedItem().set(ClientOptions.TRANSACTION);

    }

    public void gotoAccount(){
        Model.getInstance().getView().getSelectedItem().set(ClientOptions.ACCOUNT);

    }

    public void gotoPayTransfer(){
        Model.getInstance().getView().getSelectedItem().set(ClientOptions.PAY);
    }

    public void gotoLogOut(){
        Stage stage=(Stage) dashboard_btn.getScene().getWindow();
        Model.getInstance().getView().closeView(stage);
        Model.getInstance().getView().showLoginView();
        Model.getInstance().setClientFlagSet(false);
    }











}
