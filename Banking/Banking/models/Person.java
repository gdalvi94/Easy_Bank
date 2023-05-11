package com.example.banking.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

//parent class person
public class Person {
    private final StringProperty fname;
    private final StringProperty lname;
    private  final StringProperty username;

//    private final StringProperty password;

    private final ObjectProperty<LocalDate> date;

    public Person(String fname, String lname, String username, LocalDate date) {
        this.fname = new SimpleStringProperty(this,"FirstName",fname);
        this.lname = new SimpleStringProperty(this,"LastName",lname);
        this.username = new SimpleStringProperty(this,"Username",username);
//        this.password = new SimpleStringProperty(this,"Password",pass);
        this.date = new SimpleObjectProperty<>(this,"Date",date);

    }


    public StringProperty fnameProperty() {
        return fname;
    }
    public StringProperty lnameProperty() {
        return lname;
    }

    public StringProperty usernameProperty() {
        return username;
    }

//    public StringProperty passwordProperty() {
//        return password;
//    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}
