package com.example.Banking;

import javafx.application.Application;
import javafx.stage.Stage;

import com.example.Banking.models.Model;


/**
 * Hello world!
 *
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
          Model.getInstance().getView().showLoginView();

    }
}
