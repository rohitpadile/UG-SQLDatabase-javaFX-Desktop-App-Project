package com.rohitpadile.ugdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Passwords passwords = new Passwords();
    @FXML
    private PasswordField adminPasswordField;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-profile.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } else {
            adminPasswordField.clear();
        }

    }




}