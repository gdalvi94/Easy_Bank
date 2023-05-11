package com.example.Banking.controllers;

import com.example.Banking.models.Model;
import com.example.Banking.view.AccountTypes;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ImageView img;
    public Label choose_type_lbl;
    public ChoiceBox<AccountTypes> drpdown_list;
    public Label userna_lbl;
    public TextField username_input_box;
    public Label pwd_lbl;
    public TextField pwd_input_field;
    public Button login_btn;
    public Label Error_lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drpdown_list.setItems(FXCollections.observableArrayList(AccountTypes.ADMIN,AccountTypes.CLIENT));
        drpdown_list.setValue(Model.getInstance().getView().getAccountTypes());
        drpdown_list.valueProperty().addListener(observable -> Model.getInstance().getView().setAccountTypes(drpdown_list.getValue()));
        login_btn.setOnAction(event ->login());
    }

    //login controller function for login according to role and credentials
    public void login(){
        if(username_input_box.getText().equals("") || pwd_input_field.getText().equals("")){
            Error_lbl.setText("All fields Required");
        }
        else {
            Stage stage = (Stage) Error_lbl.getScene().getWindow();

            if (Model.getInstance().getView().getAccountTypes() == AccountTypes.CLIENT) {
                System.out.println("username_input_box.getText()" + username_input_box.getText());
                Model.getInstance().validation(username_input_box.getText(), pwd_input_field.getText());
                if (Model.getInstance().getClientFlagSet()) {
                    Model.getInstance().getView().showClientView();
                    Model.getInstance().getView().closeView(stage);
                } else {
                    username_input_box.setText("");
                    pwd_input_field.setText("");
                    Error_lbl.setText("User not found");
                }
            } else {
                Model.getInstance().validationAdmin(username_input_box.getText(), pwd_input_field.getText());
                if (Model.getInstance().getAdminFlagSet()) {
                    Model.getInstance().getView().showAdminView();
                    Model.getInstance().getView().closeView(stage);
                } else {
                    username_input_box.setText("");
                    pwd_input_field.setText("");
                    Error_lbl.setText("User not found");
                }

            }

        }
    }
}
