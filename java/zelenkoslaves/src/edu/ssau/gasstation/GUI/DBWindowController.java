package edu.ssau.gasstation.GUI;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import java.io.IOException;

/**
 * Created by andrey on 04.12.16.
 */
public class DBWindowController extends Application{
    @FXML private TableView car;
    private Stage primaryStage;
    private Pane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Работа с справочниками");
        initRootLayout();
        ObservableList<TableColumn> test = car.getColumns();
    }

    @FXML
    private void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DBWindowController.class.getResource("db_window.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            car = (TableView)loader.getNamespace().get("car");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
