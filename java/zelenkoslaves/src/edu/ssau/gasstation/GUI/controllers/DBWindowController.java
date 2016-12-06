package edu.ssau.gasstation.GUI.controllers;

import edu.ssau.gasstation.GUI.components.EditButtonCell;

import edu.ssau.gasstation.GUI.model.CarRecord;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.util.Callback;



/**
 * Created by andrey on 04.12.16.
 */
public class DBWindowController {
    @FXML
    private TableView<CarRecord> car;
    @FXML
    private TableColumn<CarRecord, Double> tankVolume;
    @FXML
    private TableColumn<CarRecord, String> carType;
    @FXML
    private TableColumn<CarRecord, String> carFuelType;
    @FXML
    private TableColumn<CarRecord, Boolean> editCar;
    @FXML
    private TableColumn<CarRecord, Boolean> deleteCar;
    private ObservableList<CarRecord> data = FXCollections.observableArrayList(new CarRecord(1, "Таёта", "АИ-95", 10.0));

    private Stage primaryStage;
    private Pane rootLayout;

    @FXML
    private void initialize() {
        tankVolume.setCellValueFactory(new PropertyValueFactory<CarRecord, Double>("tankVolume"));
        carType.setCellValueFactory(new PropertyValueFactory<CarRecord, String>("carType"));
        carFuelType.setCellValueFactory(new PropertyValueFactory<CarRecord, String>("carFuelType"));
        //fillData();
        editCar.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        editCar.setCellFactory(param -> new EditButtonCell(data));
        car.setItems(data);
    }
}

