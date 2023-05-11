package com.example.Banking.controllers.admin;

import com.example.Banking.models.Model;
import com.example.Banking.view.AdminOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminOptionsController implements Initializable {
    public AnchorPane anchorpane_admin_box;
    public Button create_client_btn;
    public Button client_list_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       addListeners();
    }

    //listeners to add action to button upon clicking
    private void addListeners() {
        client_list_btn.setOnAction(event -> gotoClientList());
        deposit_btn.setOnAction(event -> gotoDeposit());
        create_client_btn.setOnAction(event -> gotoAddClient());
        logout_btn.setOnAction(event -> gotoLogOut());

    }

    //function call to button listeners
    public void gotoAddClient(){
        Model.getInstance().getView().getSelectedAdminItem().set(AdminOptions.CREATE_CLIENT); // redirect to create client
    }
    public void gotoClientList(){
        Model.getInstance().getView().getSelectedAdminItem().set(AdminOptions.CLIENT_LIST);//// redirect to  client list
    }
    public void gotoDeposit(){
        Model.getInstance().getView().getSelectedAdminItem().set(AdminOptions.DEPOSIT); //// redirect to deposits
    }


    //function to logout
    public void gotoLogOut(){
        Stage stage=(Stage) client_list_btn.getScene().getWindow();
        Model.getInstance().getView().closeView(stage);
        Model.getInstance().getView().showLoginView();
        Model.getInstance().setAdminFlagSet(false);
    }

}

