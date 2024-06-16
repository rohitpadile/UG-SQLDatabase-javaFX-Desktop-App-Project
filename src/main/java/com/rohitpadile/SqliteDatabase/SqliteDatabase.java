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
    private static PreparedStatement createTableIfNotExist;
    private static PreparedStatement selectStudentWithMis;
    private static PreparedStatement deleteStudentWithMis;
    private static PreparedStatement addStudentWithDetails;

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
}
