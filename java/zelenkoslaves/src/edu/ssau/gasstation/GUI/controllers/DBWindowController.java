package edu.ssau.gasstation.GUI.controllers;

import edu.ssau.gasstation.DB.DBHelper;
import edu.ssau.gasstation.GUI.components.DeletButtonCell;
import edu.ssau.gasstation.GUI.components.EditButtonCell;

import edu.ssau.gasstation.GUI.model.CarRecord;
import edu.ssau.gasstation.GUI.model.FuelRecord;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;


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
    @FXML
    private Button addCar;
    @FXML
    private Button addFuel;
    @FXML
    private TextField addCarName;
    @FXML
    private TextField addTankVolume;
    private ObservableList<CarRecord> carList = FXCollections.observableArrayList();
    private ObservableList<FuelRecord> fuelList = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<FuelRecord> addFuelType;
    private DBHelper dbh;

    /*private Stage primaryStage;
    private Pane rootLayout;*/


    @FXML
    private void initialize() {
        dbh = new DBHelper();
        try {
            int a = dbh.getMaxID("car");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initCarList();
        initFuelList();
        initButtons();
        initTextFields();
        addFuelType.setItems(fuelList);
    }

    private void initButtons(){
        ImageView pic = new ImageView(new Image(getClass().getResourceAsStream("add.png")));
        pic.setFitHeight(23);
        pic.setFitWidth(23);
        addCar.paddingProperty().setValue(new Insets(2, 2, 2, 2));
        addCar.graphicProperty().setValue(pic);
        addCar.setPrefSize(16, 16);
        addFuel.paddingProperty().setValue(new Insets(2, 2, 2, 2));
        pic = new ImageView(new Image(getClass().getResourceAsStream("add.png")));
        pic.setFitHeight(23);
        pic.setFitWidth(23);
        addFuel.graphicProperty().setValue(pic);
        addFuel.setPrefSize(16, 16);
    }

    private void initCarList(){
        try {
            ResultSet rs = dbh.getCarList();
            while(rs.next()) {
                String name = rs.getString("car_name");
                double tankVolume = rs.getDouble("car_tank_volume");
                String fuelType = dbh.getFuelName(rs.getInt("fuel_id"));
                int recordID = rs.getInt("car_id");
                carList.add(new CarRecord(recordID, name, fuelType, tankVolume));
            }
            tankVolume.setCellValueFactory(new PropertyValueFactory<>("tankVolume"));
            carType.setCellValueFactory(new PropertyValueFactory<>("carType"));
            carFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
            editCar.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
            editCar.setCellFactory(param -> new EditButtonCell(carList));
            deleteCar.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
            deleteCar.setCellFactory(param -> new DeletButtonCell(carList));
            car.setItems(carList);
            addCar.setOnAction(event -> {
                try {
                    if(addCarName.getText() != "" && addTankVolume.getText() != "" && addFuelType.getValue() != null) {
                        int id = dbh.getMaxID("car");
                        CarRecord cr = new CarRecord(id + 1, addCarName.getText(), addFuelType.getValue().getFuelName(), Double.valueOf(addTankVolume.getText()));
                        carList.add(cr);
                        dbh.insertCar(cr);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Look, an Information Dialog");
                        alert.setContentText("I have a great message for you!");

                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initFuelList(){
        try {
            ResultSet rs = dbh.getFuelList();
            while(rs.next()) {
                String name = rs.getString("fuel_name");
                double fuelCost = rs.getDouble("fuel_cost");
                int recordID = rs.getInt("fuel_id");
                fuelList.add(new FuelRecord(recordID, name, fuelCost));
            }
            System.out.println("get list of car");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTextFields(){
        String numberMatcher = "(\\d{1,2})(\\.\\d{0,3})?";
        //t1 - новый текст, s - старый текст.
        addTankVolume.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!newValue.matches(numberMatcher)) {
                    addTankVolume.setText(oldValue);
                } else {
                    try {
                        // тут можете парсить строку как захотите
                        addTankVolume.setText(newValue);
                    } catch (NumberFormatException e) {
                        addTankVolume.setText(oldValue);
                    }
                }
            }
        });
    }

    private void initDataFactory(){

    }
}

