package com.rohitpadile.ugdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public static String currentLoggedInProfileName;
    private String deleteThisLater;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static ProfileController profileController;

    private Passwords passwords = new Passwords();
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private PasswordField userPasswordField;

    @FXML
    public void switchToAdminPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-password.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToUserPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user-password.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void enterPasswordForAdmin(ActionEvent event) throws IOException {
        String pass = adminPasswordField.getText().trim();
        if(pass.equals(passwords.getCurrentAdminPassword())) {
            currentLoggedInProfileName = "Admin";
            Parent root = FXMLLoader.load(getClass().getResource("admin-profile.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            profileController = new ProfileController();
            //Initializing Profile

        } else {
            adminPasswordField.clear();
        }
    }

    @FXML
    public void enterPasswordForUser(ActionEvent event) throws IOException {
        String pass = userPasswordField.getText().trim();
        if(pass.equals(passwords.getCurrentUserPassword())) {
            currentLoggedInProfileName = "User";
            Parent root = FXMLLoader.load(getClass().getResource("user-profile.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            profileController = new ProfileController();
            //Initializing Profile
        } else {
            userPasswordField.clear();
        }
    }




}