package com.javafxprojectday19;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import  java.sql.*;

public class JavaDatabaseConnection {

    public static void changeScence (ActionEvent event, String fxmlFile,String title, String username){
        Parent root = null;

        if(username!=null){
            try{
                FXMLLoader loader = new FXMLLoader(JavaDatabaseConnection.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                root = FXMLLoader.load(JavaDatabaseConnection.class.getResource(fxmlFile));
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root,900,500));
            stage.show();
        }
    }

    public static void signUpUser(ActionEvent event, String username,String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","2090");
            psCheckUserExists = connection.prepareStatement("select *from users where username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not user this username");
                alert.show();
            }
            else{
                String query = " insert into users (username, password)"
                        + " values (?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, password);


                // execute the preparedstatement
                preparedStmt.execute();
                System.out.println("Inserted");
                changeScence(event,"loggedIn.fxml","Welcome",username);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert!=null){
                try {
                    psInsert.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists!=null){
                try{
                    psCheckUserExists.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
