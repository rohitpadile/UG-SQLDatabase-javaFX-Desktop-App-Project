package com.rohitpadile.SqliteDatabase;

import com.rohitpadile.ugdatabase.Student;

import java.sql.*;

public class SqliteDatabase {
    private static Connection conn;
    public static final String DB_NAME = "ug-database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:/java/databases/ug database/" + DB_NAME;
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
        try (PreparedStatement selectStudentWithMis = conn.prepareStatement(PreparedStatements.SELECT_STUDENT_WITH_MIS_ID)) {
            selectStudentWithMis.setInt(1, Integer.parseInt(mis));
            ResultSet rs = selectStudentWithMis.executeQuery();
            if(rs.next()){
                //student found
                String firstName = rs.getString(2);
                String middleName = rs.getString(3);
                String lastName = rs.getString(4);
                int yoa = rs.getInt(5);
                String email = rs.getString(6);
                int mobileNumber = rs.getInt(7);
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
}
