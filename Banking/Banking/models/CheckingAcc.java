package com.example.banking.models;

import javafx.beans.property.*;

//checking account class
public class CheckingAcc extends Account{
    private final DoubleProperty transactionLimit;



    //parameterized constructor
    public CheckingAcc(String user, String accountNo, double transactionLimit, double balance) {
        super(user, accountNo, balance);
        this.transactionLimit = new SimpleDoubleProperty(this,"TransactionLimit",transactionLimit);
    }

    public DoubleProperty transactionLimitProp() {
        return transactionLimit;
    }

    @Override
    public String toString(){
        return accountNoProperty().get();
    }
}
