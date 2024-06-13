package com.rohitpadile.ugdatabase;

import com.rohitpadile.SqliteDatabase.SqliteDatabaseSingletonClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("UGConnect");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!SqliteDatabaseSingletonClass.getInstance().openConnection()){
            System.out.println("Failed to connect to database");
        } else {
            System.out.println("Database:" + SqliteDatabaseSingletonClass.DB_NAME + "connected succesfully");
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if(SqliteDatabaseSingletonClass.getInstance() != null){
            SqliteDatabaseSingletonClass.getInstance().closeConnection();
            System.out.println("Connection to database: " + SqliteDatabaseSingletonClass.DB_NAME + " closed sucessfully");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}