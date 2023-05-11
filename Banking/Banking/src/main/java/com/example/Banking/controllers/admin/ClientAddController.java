package com.example.Banking.controllers.admin;

import com.example.Banking.models.Model;
import com.example.Banking.view.AccountTypes;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class ClientAddController implements Initializable {
    public TextField fname_field;
    public TextField lname_field;
    public TextField pwd_field_client;
    public TextField balance_sav_field;
    public TextField balance_check_field;
    public Button create_acc_btn;
    public CheckBox checkbox_savings;
    public CheckBox checkbox_checking;
    public Label error_lbl;

    // username
    public TextField user_id_txt;

    public Label user_lbl;


    public String username1;
    private boolean addCheckingAccSetFlag = false;
    private boolean addSavingAccSetFlag = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_acc_btn.setOnAction(event -> addClient());
        checkbox_checking.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                addCheckingAccSetFlag = true;
            }
        });
        checkbox_savings.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                addSavingAccSetFlag = true;
            }
        });

    }

    //function to add new client
    private void addClient() {

        String fname = fname_field.getText();
        String lname = lname_field.getText();
        String password = pwd_field_client.getText();
        String username = user_id_txt.getText();
        if(fname.equals("") || lname.equals("") || password.equals("")|| username.equals("") || balance_check_field.getText().equals("") || balance_sav_field.getText().equals("") || !checkbox_checking.isSelected() || !checkbox_savings.isSelected()){
            error_lbl.setText("All fields are required");
        }
        else {
            username1 = username;
            if (addCheckingAccSetFlag) {
                addAccount("CheckingAcc");
            }
            if (addSavingAccSetFlag) {
                addAccount("SavingAcc");
            }
            // Client Info
            System.out.println("Output" + fname + lname + password + username);
            Model.getInstance().getDatabaseDriver().addClient(fname, lname, username, password, LocalDate.now());
            error_lbl.setText("Client Data Added Successfully");
            emptyFields();
            System.out.println("username new" + username1);
        }
    }


    //function to add new account in create client
    private void addAccount(String accountType) {
        double amount = Double.parseDouble(balance_check_field.getText());
        double amount_savings = Double.parseDouble(balance_sav_field.getText());

        String initialNumber = "3201";
        String lastNumber = Integer.toString((new Random()).nextInt(9999) + 1000);
        String accNumber = initialNumber + " " + lastNumber;
        System.out.println("username new" + username1);
            if (accountType.equals("CheckingAcc")) {
                System.out.println("username new1" + username1);
                Model.getInstance().getDatabaseDriver().addCheckingAcc(username1, accNumber, 10000.0, amount);
            } else {
                Model.getInstance().getDatabaseDriver().addSavingAcc(username1, accNumber, 20000.0, amount_savings);
            }



    }


    //function to clear fields
private void emptyFields() {
    fname_field.setText("");
    lname_field.setText("");
    pwd_field_client.setText("");
    checkbox_checking.setSelected(false);
    checkbox_savings.setSelected(false);
    balance_check_field.setText("");
    user_id_txt.setText("");
    balance_sav_field.setText("");


}
}
