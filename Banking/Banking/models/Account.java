package com.example.banking.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//Account class
public class Account {
    private final StringProperty user;
    private final StringProperty accountNo;
    private final DoubleProperty balance;


    //parameterized constructor
    public Account(String user, String accountNo, double balance) {
        this.user = new SimpleStringProperty(this,"Username",user);
        this.accountNo = new SimpleStringProperty(this,"AccountNo",accountNo);
        this.balance = new SimpleDoubleProperty(this,"Amount",balance);
    }

    public StringProperty userProperty() {
        return user;
    }


    public StringProperty accountNoProperty() {
        return accountNo;
    }

    public DoubleProperty balanceProperty() {return balance;}

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

}
