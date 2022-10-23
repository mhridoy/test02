package com.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class JDBC {
    public static  void changeScene(ActionEvent event, String fxmlFile, String title, String username, String hobby){
        Parent root = null;
        if(username!=null && hobby !=null){
            try{
                FXMLLoader loader = new FXMLLoader(JDBC.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username,hobby);

            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        else {
            try{
                root = FXMLLoader.load(JDBC.class.getResource(fxmlFile));

            }catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root,900,500));
            stage.show();
        }
    }
    public static void signUpUser(ActionEvent event, String username,String password, String hobby){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","2090");
            psCheckUserExists = connection.prepareStatement("*SELECT *FROM users WHERE username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not user this username");
                alert.show();
            }
            else{
                psInsert = connection.prepareStatement("*INSERT INTO users (username,password, hobby) VALUES (?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,hobby);
                psInsert.executeQuery();

                changeScene(event,"loggedIn.fxml","Welcome",username,hobby);

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
    public static void logInUser(ActionEvent event,String username,String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","2090");
            preparedStatement = connection.prepareStatement("*SELECT password, hobby FROM users WHERE username =?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User is not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("incorrect");
                alert.show();
            }
            else{
                while (resultSet.next()){
                    String retrivedPassword = resultSet.getString("password");
                    String retrivedHobby = resultSet.getString("hobby");

                    if(retrivedPassword.equals(password)){
                        changeScene(event,"loggedIn.fxml","Welcome",username,retrivedHobby);
                    }
                    else{
                        System.out.println("Incoreesct pass");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("incorrect");
                        alert.show();
                    }
                }

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
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
