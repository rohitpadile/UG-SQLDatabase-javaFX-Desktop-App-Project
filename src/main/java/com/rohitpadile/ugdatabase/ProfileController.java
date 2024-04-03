package com.rohitpadile.ugdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ProfileController {
//    @FXML
//    private Button findStudentButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button editStudentButton;
    @FXML
    private Button deleteStudentButton;
    @FXML
    private Button deleteStudentButtonConfirm = new Button();
    @FXML
    private TextField findStudentMisField;
    @FXML
    private TextField addStudentMisField;

//    @FXML
//    private Label misDisplayLabel = new Label();
//    @FXML
//    private Label nameDisplayLabel = new Label();
//    @FXML
//    private Label yoaDisplayLabel = new Label();
//    @FXML
//    private Label emailDisplayLabel = new Label();
//    @FXML
//    private Label phoneDisplayLabel = new Label();
//    @FXML
//    private Label addressDisplayLabel = new Label();

    @FXML
    private TextArea displayStudentDetailsTextArea = new TextArea();
    //Initialize Field outside of the methods only.

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String entered_mis_ForFindStudent;
    //This is public entered_mis entered in MIS FIELD of Find Student and Delete Student Method
    public static String entered_mis_ForAddStudent;




    @FXML
    private Button addStudentInputDetailsSaveButton = new Button();
    @FXML
    private TextField addStudentFirstNameField = new TextField();
    @FXML
    private TextField addStudentLastNameField = new TextField();
    @FXML
    private TextField addStudentMiddleNameField = new TextField();
    @FXML
    private TextField addStudentYOAField = new TextField();
    @FXML
    private TextField addStudentEmailAddressField = new TextField();
    @FXML
    private TextField addStudentMobileNumberField = new TextField();
    @FXML
    private TextField addStudentHomeAddressField = new TextField();


    protected static final Map<String, Student> dataMap = new HashMap<>();

    static  {
        // Load the Data as soon as the ProfileController is opened
        // Load the data from the csv file into the hashmap 'dataMap'

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

        System.out.println("loadCSVData is successful. dataMap is updated successfully");
        System.out.println(dataMap);
    }

    public static void loadMapData(String filename, Map<String,Student> dataMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for(Map.Entry<String, Student> entry : dataMap.entrySet()) {

            Student student = entry.getValue();
            String line = student.getMis() + "," +
                    student.getFirstName() + "," +
                    student.getMiddleName() + "," +
                    student.getLastName() + "," +
                    student.getYearOfAdmission() + "," +
                    student.getEmail() + "," +
                    student.getMobileNumber() + "," +
                    student.getHomeAddress();

            writer.write(line);
            writer.newLine();
        }
        writer.close();
        System.out.println("CSV file updated Successfully");

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
            String pattern1 = "^6122\\d{5}$";
            String pattern2 = "^6423\\d{5}$";
//            if(Integer.parseInt(findStudentMisField.getText().trim())); //Checking if the entered mis is Integer
//            int checkMis;

//            String checkMis_ = String.valueOf(checkMis);
            String checkMis_ = findStudentMisField.getText().trim();
            if(checkMis_.length() == 9 && (checkMis_.matches(pattern1) || checkMis_.matches(pattern2))) {
                System.out.println("Find Student Method: Enter MIS is valid and of the form 612211066 or 642311066");
            } else {
                throw new IOException();
            }
        } catch (IOException | ClassCastException e){
            findStudentMisField.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Mis");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Mis");

            alert.showAndWait();
            return;
        }

        //entered_mis_ForFindStudent from admin
        entered_mis_ForFindStudent = findStudentMisField.getText().trim();

        if (dataMap.containsKey(entered_mis_ForFindStudent)) {
//                root = FXMLLoader.load(getClass().getResource("displayStudentDetails-page.fxml"));
//                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            System.out.println("MIS is present in the dataMap");
//
//            System.out.println(entered_mis);
//            System.out.println(dataMap);

            try {
                displayStudentDetailsTextArea.setText("" +
                        "MIS: \t\t\t\t\t" + dataMap.get(entered_mis_ForFindStudent).getMis() + "\n" +
                        "NAME: \t\t\t\t" + dataMap.get(entered_mis_ForFindStudent).getFirstName() + " " +
                        dataMap.get(entered_mis_ForFindStudent).getMiddleName() + " " +
                        dataMap.get(entered_mis_ForFindStudent).getLastName() + "\n" +
                        "YEAR OF ADMISSION: \t" + dataMap.get(entered_mis_ForFindStudent).getYearOfAdmission() + "\n" +
                        "EMAIL: \t\t\t\t" + dataMap.get(entered_mis_ForFindStudent).getEmail() + "\n" +
                        "PHONE: \t\t\t\t" + dataMap.get(entered_mis_ForFindStudent).getMobileNumber() + "\n" +
                        "ADDRESS: \t\t\t" + dataMap.get(entered_mis_ForFindStudent).getHomeAddress() + "\n");
            } catch (NullPointerException e) {
                System.out.println("Null pointer exception");
            }



        } else {
            //open a Alert dialog which says student not found.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MIS NOT FOUND!");
            alert.setHeaderText(null);
            alert.setContentText("Student with Mis " + findStudentMisField.getText().trim() + " not found!");

            alert.showAndWait();
        }

    }


    @FXML
    public void deleteStudentButtonHandle(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE STUDENT DATA?");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to confirm delete Student with MIS: " + entered_mis_ForFindStudent);

        ButtonType confirmButton = new ButtonType("Confirm");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        //We usually deal with button in Alert dialog. Trend hai bhai!

        if(result.isPresent() && result.get() == confirmButton) {
            System.out.println("Deleting Student....");
            dataMap.remove(entered_mis_ForFindStudent); //Removed Student from dataMap
            try {
                loadMapData("UGData/ugstudentdata.csv", dataMap); //Updating the csv file with dataMap
            } catch (IOException e) {
                System.out.println("Cannot update CSV FILE");
            } finally {
                System.out.println("Finally block is run successfully");
            }
        } else  {
            System.out.println("Canceling Deletion");
        }

    }

    @FXML
    public void addStudentMisInputHandle(ActionEvent event) throws IOException {
        //Verifying if the MIS is valid
        try {
            String pattern1 = "^6122\\d{5}$";
            String pattern2 = "^6423\\d{5}$";
//            if(Integer.parseInt(findStudentMisField.getText().trim())); //Checking if the entered mis is Integer
//            int checkMis;

//            String checkMis_ = String.valueOf(checkMis);
            String checkMis_ = addStudentMisField.getText().trim();
            if(checkMis_.length() == 9 && (checkMis_.matches(pattern1) || checkMis_.matches(pattern2))) {
                System.out.println("Add Student Method: Enter MIS is valid and of the form 612211066 or 642311066");
            } else {
                throw new IOException();
            }
        } catch (IOException | ClassCastException e){
            addStudentMisField.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Mis");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Mis");

            alert.showAndWait();
            return;
        }

        //entered_mis_ForFindStudent from admin
        this.entered_mis_ForAddStudent = addStudentMisField.getText().trim();

        if (dataMap.containsKey(entered_mis_ForAddStudent)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MIS ALREADY PRESENT");
            alert.setHeaderText(null);
            alert.setContentText("Student with MIS + " + entered_mis_ForAddStudent + " already present in the Database!");

            alert.showAndWait();
        } else {
            //Redirect the page to a new Page to get the Validated Data Input
            System.out.println("Mis entered for adding is: " +  entered_mis_ForAddStudent);
            root = FXMLLoader.load(getClass().getResource("addStudentInputDetails-page.fxml"));
//            root = FXMLLoader.load(getClass().getResource("addStudent-page.fxml"));
//            root = FXMLLoader.load(getClass().getResource("findStudent-page.fxml"));
            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //Hovering off to AddStudentInputDetailsController

//            AddStudentInputDetailsController addStudentInputDetailsController = new AddStudentInputDetailsController(entered_mis_ForAddStudent);
        }

    }


    @FXML
    public void addStudentInputDetailsSaveButtonHandle(ActionEvent event) throws IOException {
        System.out.println("Mis entered for adding is: " +  this.entered_mis_ForAddStudent);
         try {
            if (addStudentFirstNameField.getText().trim().matches("[a-zA-Z]+") &&
                    addStudentMiddleNameField.getText().trim().matches("[a-zA-Z]+") &&
                    addStudentLastNameField.getText().trim().matches("[a-zA-Z]+")) {
                System.out.println("Name is valid");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {

            System.out.println("Invalid Input");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INVALID NAME INPUT");
            alert.setHeaderText(null);
            alert.setContentText("Please check First,Middle,Last Name.");

            alert.showAndWait();
            return;
        }

        try {
            if (addStudentYOAField.getText().trim().length() == 4 && addStudentYOAField.getText().trim().matches("[0-9]+")) {
                System.out.println("The year of admission is valid.");
            } else {
                throw new IOException();
            }

        }catch (IOException e) {
//            System.out.println();
            System.out.println("Invalid Year of Admission");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INVALID YEAR OF ADMISSION");
            alert.setHeaderText(null);
            alert.setContentText("Please check Year of Admission");

            alert.showAndWait();
            return;
        }

        try {
            String emailPattern = "[a-zA-Z0-9._%+-]+@coeptech\\.ac\\.in";
            if (addStudentEmailAddressField.getText().trim().matches(emailPattern)) {
                System.out.println("The email address is valid.");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Invalid email address.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INVALID EMAIL ADDRESS");
            alert.setHeaderText(null);
            alert.setContentText("Please check your email address");

            alert.showAndWait();
            return;
        }

        try {
            if (addStudentMobileNumberField.getText().trim().length() == 10 &&
                    addStudentMobileNumberField.getText().trim().matches("[0-9]+")) {
                System.out.println("The mobile number is valid.");
            } else {
                throw new IOException();
            }

        }catch (IOException e) {
            System.out.println("Invalid mobile number.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INVALID MOBILE NUMBER");
            alert.setHeaderText(null);
            alert.setContentText("Please check your mobile address");

            alert.showAndWait();
            return;
        }


        try {
            if (addStudentHomeAddressField.getText().trim().length() >= 5) {
                System.out.println("Valid address");
            } else {
                throw new IOException();
            }

        }catch (IOException e) {
            System.out.println("Very short address");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("VERY SHORT ADDRESS");
            alert.setHeaderText(null);
            alert.setContentText("Please enter address for minimum 5 characters");

            alert.showAndWait();
            return;
        }


        try {
            String firstName = addStudentFirstNameField.getText().trim();
            String middleName = addStudentMiddleNameField.getText().trim();
            String lastName = addStudentLastNameField.getText().trim();
            String yearOfAdmission = addStudentYOAField.getText().trim();
            String emailAddress = addStudentEmailAddressField.getText().trim();
            String mobileNumber = addStudentMobileNumberField.getText().trim();
            String homeAddress = addStudentHomeAddressField.getText().trim();

            // Perform null checks on text fields
            if (firstName.isEmpty() || lastName.isEmpty() || yearOfAdmission.isEmpty() || emailAddress.isEmpty() ||
                    mobileNumber.isEmpty() || homeAddress.isEmpty()) {
                throw new NullPointerException("One or more fields are empty.");
            }

            // Proceed with creating the student object and adding it to the map
            Student student = new Student(
                    this.entered_mis_ForAddStudent,
                    firstName,
                    middleName,
                    lastName,
                    yearOfAdmission,
                    emailAddress,
                    mobileNumber,
                    homeAddress);
            System.out.println("New student to Add is successfully created");
            dataMap.put(entered_mis_ForAddStudent, student);
            System.out.println("New student to Add is successfully put in dataMap");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText(null);
            alert.setContentText("NEW STUDENT WITH MIS " + entered_mis_ForAddStudent + " ADDED SUCCESSFULLY");

            alert.showAndWait();

            loadMapData("UGData/ugstudentdata.csv", dataMap); //Updating the csv file with dataMap
            System.out.println("Updated the csv file!");

        } catch (NullPointerException e) {
            System.out.println("New student to Add is unsuccessful: " + e.getMessage());
            // Optionally show an alert or message to the user indicating that one or more fields are empty

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UNSUCCESSFUL");
            alert.setHeaderText(null);

            alert.showAndWait();
//            alert.setContentText("Please enter address for minimum 5 characters");
        }

    }


}
