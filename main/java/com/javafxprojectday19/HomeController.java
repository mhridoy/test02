package com.javafxprojectday19;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

@FXML
private Button button_login;

@FXML
private  Button button_signup;


@FXML
private Label check;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                check.setText("I'm clicked!");
            }
        });
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JavaDatabaseConnection.changeScence(event,"Signup.fxml","User Registration",null);
            }
        });

    }
}