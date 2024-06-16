package com.rohitpadile.SqliteDatabase;

public class PreparedStatements {
    public static final String CREATE_TABLE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS " + " ug_students " + "(\n" +
                                                            "    mis_id INTEGER PRIMARY KEY,\n" +
                                                            "    first_name TEXT,\n" +
                                                            "    middle_name TEXT,\n" +
                                                            "    last_name TEXT,\n" +
                                                            "    year_of_admission INTEGER,\n" +
                                                            "    email TEXT,\n" +
                                                            "    mobile_number TEXT,\n" +
                                                            "    home_address TEXT,\n" +
                                                            "    type TEXT\n" +
                                                            ");";

    public static final String SELECT_STUDENT_WITH_MIS_ID =  "SELECT * FROM ug_students WHERE mis_id = ?";
    public static final String DELETE_STUDENT_WITH_MIS_ID =  "DELETE FROM ug_students WHERE mis_id = ?";
    public static final String ADD_STUDENT_WITH_ALL_DETAILS = "INSERT INTO ug_students (mis_id, first_name, middle_name, last_name, year_of_admission, email, mobile_number, home_address) VALUES (?, ?, ?,?, ?, ?,?, ?)";
    }