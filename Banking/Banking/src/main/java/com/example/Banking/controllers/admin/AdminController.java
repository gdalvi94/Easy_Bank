package com.example.Banking.controllers.admin;

import com.example.Banking.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;
//admin controller for selection of admin menu
public class AdminController implements Initializable {
    public BorderPane admin_window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getView().getSelectedAdminItem().addListener(((observable, oldValue, newValue) -> {
            switch (newValue){
                //choice to select and it will show the window upon selection
                case  CLIENT_LIST-> admin_window.setCenter(Model.getInstance().getView().showClientList());
                case DEPOSIT -> admin_window.setCenter(Model.getInstance().getView().getDepositView());
                default -> admin_window.setCenter(Model.getInstance().getView().showClientAdd());
            }
        }));
    }
}
