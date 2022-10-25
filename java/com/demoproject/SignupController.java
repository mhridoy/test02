package com.demoproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;
    @FXML
    private RadioButton rb_hobby_code;
    @FXML
    private  RadioButton rb_hobby_something;


    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ToggleGroup toggleGroup = new ToggleGroup();
        rb_hobby_code.setToggleGroup(toggleGroup);
        rb_hobby_something.setToggleGroup(toggleGroup);

        rb_hobby_code.setSelected(true);
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    JDBC.signUpUser(actionEvent,tf_username.getText(),tf_password.getText(),toggleName);
                    JDBC.changeScene(actionEvent,"loggedIn.fxml","Welcome", String.valueOf(tf_username),toggleName);
                    System.out.println("Hello!");
                }
                else{
                    System.out.println("Please fill the all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill the all info");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                JDBC.changeScene(actionEvent,"hello-view.fxml","Log In",null,null);

            }
        });
    }
}
