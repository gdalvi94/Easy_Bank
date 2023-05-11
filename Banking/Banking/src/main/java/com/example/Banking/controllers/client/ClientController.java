package com.example.Banking.controllers.client;

import com.example.Banking.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getView().getSelectedItem().addListener(((observable, oldValue, newValue) -> {
            switch (newValue){
                case TRANSACTION -> client_id.setCenter(Model.getInstance().getView().showViewTransaction());
                case ACCOUNT -> client_id.setCenter(Model.getInstance().getView().showViewAccount());
                case PAY -> client_id.setCenter(Model.getInstance().getView().showPayMoneyView());
                default -> client_id.setCenter(Model.getInstance().getView().showViewDashboard());
            }
        }));
    }
}
