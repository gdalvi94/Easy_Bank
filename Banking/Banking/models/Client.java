package com.example.banking.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Date;

//Client class
public class Client extends Person{

    private final ObjectProperty<Account> checkingAcc;
    private final ObjectProperty<Account> savingAcc;



    //parameterized constructor
    public Client(String fname, String lname, String username, LocalDate date, Account checkingAcc, Account savingAcc) {
        super(fname, lname, username, date);
        this.checkingAcc = new SimpleObjectProperty<>(this,"CheckingAcc",checkingAcc);
        this.savingAcc = new SimpleObjectProperty<>(this,"SavingAcc",savingAcc);

    }

    public ObjectProperty<Account> checkingAccProperty() {
        return checkingAcc;
    }


    public ObjectProperty<Account> savingAccProperty() {
        return savingAcc;
    }

}
