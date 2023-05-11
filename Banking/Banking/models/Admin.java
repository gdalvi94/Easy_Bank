package com.example.banking.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;


//admin class
public class Admin extends Person{
    public Admin(String fname, String lname, String username, LocalDate date) {
        super(fname, lname, username, date);
    }
}
