package edu.ssau.gasstation.GUI;

import edu.ssau.gasstation.DB.DBHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrey on 05.12.16.
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/db_window.fxml"));
        primaryStage.setTitle("Управление справочниками");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        DBHelper db = new DBHelper();
        try {
            //ResultSet rs = db.executeQuery("INSERT INTO fuel (fuel_name) VALUES(АИ-76);");
            boolean succes = db.insertFuel("АИ-76", 15.0);
            db.executeQuery("SELECT * FROM fuel;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
