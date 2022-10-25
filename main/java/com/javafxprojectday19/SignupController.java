package com.javafxprojectday19;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private Button button_login;
    @FXML
    private  Button button_signup;
    @FXML
    private TextField tf_username;
    @FXML
    private  TextField tf_password;
    @FXML
    private  Label check;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // check.setText("Clicked!");
                JavaDatabaseConnection.changeScence(event,"home.fxml","User Login",null);
            }
        });
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JavaDatabaseConnection.signUpUser(event,tf_username.getText(),tf_password.getText());
            }
        });
    }
}
