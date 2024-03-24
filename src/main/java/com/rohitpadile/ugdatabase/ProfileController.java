package com.rohitpadile.ugdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ProfileController {
    @FXML
    private Button findStudentButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button editStudentButton;
    @FXML
    private Button deleteStudentButton;
    @FXML
    private TextField findStudentMisField;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static final Map<String, Student> dataMap = new HashMap<>();

    static  {
        // Load the Data as soon as the ProfileController is opened
        // Load the data from the csv file into the hashmap 'data'

        try {
            loadCSVData("UGData/ugstudentdata.csv");
        } catch (IOException e) {
            System.out.println("Problem in static initializer block of ProfileController (filename)");
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finally block is Run successfully");
        }


    }

    public static void loadCSVData(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            Student student = new Student(
                    fields[0], // MIS
                    fields[1], // First Name
                    fields[2], // Middle Name
                    fields[3], // Last Name
                    fields[4], // Year of Admission
                    fields[5], // Email
                    fields[6], // Mobile Number
                    fields[7]  // Home Address
            );

            dataMap.put(fields[0], student);
        }

        System.out.println("loadCSVData is successful");
        System.out.println(dataMap);
    }
    @FXML
    public void addStudent(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("addStudent-page.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void findStudent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("findStudent-page.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void editStudent(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("editStudent-page.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void deleteStudent(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("deleteStudent-page.fxml"));
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void findStudentMisInputHandle(ActionEvent event) throws IOException{

        //Verifying if the MIS is valid
        try {
            String pattern = "^6122\\d{5}$";
//            if(Integer.parseInt(findStudentMisField.getText().trim())); //Checking if the entered mis is Integer
//            int checkMis;

//            String checkMis_ = String.valueOf(checkMis);
            String checkMis_ = findStudentMisField.getText().trim();
            if(checkMis_.length() == 9 && checkMis_.matches(pattern)) {
                System.out.println("Enter MIS is valid and of the form 612211066");
            } else {
                throw new IOException();
            }
        } catch (IOException | ClassCastException e){
            findStudentMisField.clear();
        }


        //Checking the MIS in the dataMap
        try {
            if (dataMap.containsKey(findStudentMisField.getText().trim())) {
                root = FXMLLoader.load(getClass().getResource("displayStudentDetails-page.fxml"));
                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                //open a dialog which says student not found.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Mis");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Mis");

                alert.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
