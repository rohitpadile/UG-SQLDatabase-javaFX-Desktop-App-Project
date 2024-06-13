package com.rohitpadile.SqliteDatabase;

import java.net.ConnectException;
import java.sql.*;

public class SqliteDatabaseSingletonClass {
    private Connection conn;
    public static final String DB_NAME = "ug-database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:/java/databases/ug database/" + DB_NAME;
    private static SqliteDatabaseSingletonClass instance = new SqliteDatabaseSingletonClass();

    private SqliteDatabaseSingletonClass(){
        //private constructor
    }

    public static SqliteDatabaseSingletonClass getInstance() { //getter for instance
        if(instance == null){
            instance = new SqliteDatabaseSingletonClass();
        }
        return instance;
    }

    public boolean openConnection(){
        //open the sqlite connection
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
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
}
