package com.demoproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button button_login;
    @FXML

    private TextField tf_username;
   @FXML
   private Button button_signup;

    @FXML

    private  TextField tf_password;



//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                JDBC.logInUser(actionEvent,tf_username.getText(),tf_password.getText());
                JDBC.changeScene(actionEvent,"loggedIn.fxml","Logged In", null,null);
                System.out.println("Clicked");


            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                JDBC.changeScene(actionEvent,"Signup.fxml","Sign Up",null,null);
            }
        });
    }

//    public void button_signup(ActionEvent actionEvent) {
//        JDBC.changeScene(actionEvent,"Signup.fxml","Sign Up",null,null);
//
//
//    }
}