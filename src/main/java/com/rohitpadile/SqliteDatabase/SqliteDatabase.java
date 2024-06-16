package com.rohitpadile.SqliteDatabase;

import com.rohitpadile.ugdatabase.Student;

import java.sql.*;
//prompts link - https://chatgpt.com/c/9fb66c5e-0114-4f11-9a2b-163cd249a09e
public class SqliteDatabase {
    private static Connection conn;
    public static final String DB_NAME = "ug-database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/Users/Rohit Padile/Desktop/" + DB_NAME;
    private static SqliteDatabase instance = new SqliteDatabase();
    private SqliteDatabase(){
        //private constructor
    }

    public static SqliteDatabase getInstance() { //getter for instance
        if(instance == null){
            instance = new SqliteDatabase();
        }
        return instance;
    }
    //Prepared Statements:
//    private static PreparedStatement createTableIfNotExist;
    private static PreparedStatement selectStudentWithMis;
    private static PreparedStatement deleteStudentWithMis;
    private static PreparedStatement addStudentWithDetails;
    private static PreparedStatement editStudentFirstnameWithMis;
    private static PreparedStatement editStudentMiddlenameWithMis;
    private static PreparedStatement editStudentLastnameWithMis;
    private static PreparedStatement editStudentYOAWithMis;
    private static PreparedStatement editStudentEmailWithMis;
    private static PreparedStatement editStudentMobilenumberWithMis;
    private static PreparedStatement editStudentHomeaddressWithMis;

    public boolean openConnection(){
        //open the sqlite connection
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            createTableIfNotExists();
            selectStudentWithMis = conn.prepareStatement(PreparedStatements.SELECT_STUDENT_WITH_MIS_ID);
            return true;
        }catch (Exception e){
            System.out.println("Open connection error : " + e.getMessage());
            return false;
        }
    }

    public boolean closeConnection() throws SQLException {
        if(conn != null) {
            conn.close();
            return true;
        }
        return false;
    }

    public static void createTableIfNotExists() {
        try (PreparedStatement createTable = conn.prepareStatement(PreparedStatements.CREATE_TABLE_IF_NOT_EXIST)) {
            createTable.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Create table error: " + e.getMessage());
        }
    }

    public static Student selectStudentWithMis(String mis) {
        try {
            selectStudentWithMis = conn.prepareStatement(PreparedStatements.SELECT_STUDENT_WITH_MIS_ID);
            selectStudentWithMis.setInt(1, Integer.parseInt(mis));
            ResultSet rs = selectStudentWithMis.executeQuery();
            if(rs.next()){
                //student found
                String firstName = rs.getString(2);
                String middleName = rs.getString(3);
                String lastName = rs.getString(4);
                int yoa = rs.getInt(5);
                String email = rs.getString(6);
                String mobileNumber = rs.getString(7);
                String address = rs.getString(8);
                Student student = new Student(Integer.parseInt(mis), firstName, middleName, lastName, yoa , email, mobileNumber,address);

                System.out.println("Fetching student from DATABASE-SQLITE");
                return student;
            }
        } catch (SQLException e) {
            System.out.println("selectStudentWithMis statement error : " + e.getMessage());
        }
        //student not found
        return null;
    }

    public static Boolean deleteStudentWithMis(String mis) {
        try {
            deleteStudentWithMis = conn.prepareStatement(PreparedStatements.DELETE_STUDENT_WITH_MIS_ID);
            deleteStudentWithMis.setInt(1, Integer.parseInt(mis));
            return deleteStudentWithMis.execute();

        } catch (SQLException e) {
            System.out.println("deleteStudentWithMis statement error : " + e.getMessage());
        }
        //student not found
        return null;
    }

    public static boolean addStudentWithDetails(String mis, String fn, String mn, String ln, int yoa, String email, String mob, String ha) {
        try {
            addStudentWithDetails = conn.prepareStatement(PreparedStatements.ADD_STUDENT_WITH_ALL_DETAILS);
            addStudentWithDetails.setInt(1, Integer.parseInt(mis));
            addStudentWithDetails.setString(2,fn );
            addStudentWithDetails.setString(3,mn );
            addStudentWithDetails.setString(4,ln );
            addStudentWithDetails.setInt(5, yoa);
            addStudentWithDetails.setString(6, email);
            addStudentWithDetails.setString(7, mob );
            addStudentWithDetails.setString(8,  ha);
            addStudentWithDetails.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("addStudentWithDetails() statement error : " + e.getMessage());
            return false;
        }
    }

    public static boolean editStudentFirstnameWithMis(String mis, String firstName) {
        try {
            editStudentFirstnameWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_FIRSTNAME_WITH_MIS_ID);
            editStudentFirstnameWithMis.setString(1, firstName);
            editStudentFirstnameWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentFirstnameWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentFirstnameWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentMiddlenameWithMis(String mis, String middleName) {
        try {
            editStudentMiddlenameWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_MIDDLENAME_WITH_MIS_ID);
            editStudentMiddlenameWithMis.setString(1, middleName);
            editStudentMiddlenameWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentMiddlenameWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentMiddlenameWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentLastnameWithMis(String mis, String lastName) {
        try {
            editStudentLastnameWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_LASTNAME_WITH_MIS_ID);
            editStudentLastnameWithMis.setString(1, lastName);
            editStudentLastnameWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentLastnameWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentLastnameWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentYOAWithMis(String mis, int yoa) {
        try {
            editStudentYOAWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_YOA_WITH_MIS_ID);
            editStudentYOAWithMis.setInt(1, yoa);
            editStudentYOAWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentYOAWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentYOAWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentEmailWithMis(String mis, String email) {
        try {
            editStudentEmailWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_EMAIL_WITH_MIS_ID);
            editStudentEmailWithMis.setString(1, email);
            editStudentEmailWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentEmailWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentEmailWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentMobilenumberWithMis(String mis, String mobileNumber) {
        try {
            editStudentMobilenumberWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_MOBILENUMBER_WITH_MIS_ID);
            editStudentMobilenumberWithMis.setString(1, mobileNumber);
            editStudentMobilenumberWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentMobilenumberWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentMobilenumberWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
    public static boolean editStudentHomeaddressWithMis(String mis, String homeAddress) {
        try {
            editStudentHomeaddressWithMis = conn.prepareStatement(PreparedStatements.EDIT_STUDENT_COLUMN_HOMEADDRESS_WITH_MIS_ID);
            editStudentHomeaddressWithMis.setString(1, homeAddress);
            editStudentHomeaddressWithMis.setInt(2, Integer.parseInt(mis));
            return editStudentHomeaddressWithMis.execute();

        } catch (SQLException e) {
            System.out.println("editStudentHomeaddressWithMis() statement error: " + e.getMessage());
            return false;
        }
    }
}
