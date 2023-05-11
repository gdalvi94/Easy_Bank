package com.example.banking.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

//saving class
public class SavingAcc extends Account{
    private final DoubleProperty withdrawLimit;

    //parameterized constructor
    public SavingAcc(String user, String accountNo, double withdrawLimit, double balance) {
        super(user, accountNo, balance);
        this.withdrawLimit = new SimpleDoubleProperty(this,"WithdrawLimit",withdrawLimit);
    }




    public DoubleProperty withdrawLimitProperty() {
        return withdrawLimit;
    }
    @Override
    public String toString(){
        return accountNoProperty().get();
    }
}
