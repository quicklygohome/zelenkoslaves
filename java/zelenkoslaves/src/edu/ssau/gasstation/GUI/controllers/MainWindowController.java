package edu.ssau.gasstation.GUI.controllers;

import edu.ssau.gasstation.GUI.components.ImageCell;
import edu.ssau.gasstation.topology.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;


/**
 * Created by andrey on 18.12.16.
 */


public class MainWindowController {
    ObservableList<String> topologyItem = FXCollections.observableArrayList("Въезд", "Выезд", "ТРК", "Резервуар", "Касса");
    @FXML
    private ListView<String> itemList;
    @FXML
    private GridPane constructorField;
    @FXML
    private Pane constructor;
    @FXML
    private Menu dbButton;
    @FXML
    private VBox settings;
    @FXML
    private MenuBar menuBar;
    private Topology topology = new Topology(6, 6);
    @FXML
    private void initialize() {
        fillList();
        addMenuBarActions();
        addConstructorField(6, 6);
    }

    private String getImageByName(String name){
        String image = "images/";
        switch (name){
            case "Въезд":
                image += "in.png";
                break;
            case "Въезд-small":
                image += "in-small.png";
                break;
            case "Выезд":
                image += "out.png";
                break;
            case "Выезд-small":
                image += "out-small.png";
                break;
            case "ТРК":
                image += "dispenser.png";
                break;
            case "ТРК-small":
                image += "dispenser-small.png";
                break;
            case "Резервуар":
                image += "tank.png";
                break;
            case "Резервуар-small":
                image += "tank-small.png";
                break;
            case "Касса":
                image += "office.png";
                break;
            case "Касса-small":
                image += "office-small.png";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return image;
    }

    private void fillList(){
        itemList.setCellFactory(listView -> new ListCell<String>(){
            private Image img;
            private ImageView pic;
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    img = new Image(getClass().getResourceAsStream(getImageByName(name)));
                    pic = new ImageView(img);
                    pic.setFitWidth(100);
                    pic.setFitHeight(100);
                    setText(name);
                    setGraphic(pic);
                    setOnDragDetected(event -> {
                        Dragboard db = this.startDragAndDrop(TransferMode.ANY);
                        //db.setDragView(img);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putImage(new Image(getClass().getResourceAsStream(getImageByName(name + "-small"))));
                        db.setContent(cc);
                        event.consume();
                    });
                }
            }
        });
        itemList.setItems(topologyItem);
    }

    private void addMenuBarActions(){
        Label dbLabel = new Label("БД");
        dbLabel.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../view/DBWindow.fxml"));
                primaryStage.setTitle("Работа с справочниками");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        dbButton.setGraphic(dbLabel);
    }

    private void addConstructorField(int rowCount, int columnCount){
        constructorField = new GridPane();

        double gridWidth = 0;
        double gridHeight = 0;
        for(int i = 0; i < columnCount; i++){
            ColumnConstraints column = new ColumnConstraints(50);
            constructorField.getColumnConstraints().add(column);
            gridWidth += 50;
        }
        for(int i = 0; i < rowCount; i++){
            RowConstraints row = new RowConstraints(50);
            constructorField.getRowConstraints().add(row);
            gridHeight += 50;
        }
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++){
                ImageCell ic = new ImageCell(i, j, 50, 50);
                Boolean isSet = false;
                ic.getCanvas().setOnDragEntered(event -> {
                    if(!ic.isSet()) {
                        ic.setTarget();
                    }
                    event.consume();
                });
                ic.getCanvas().setOnDragExited(event -> {
                    if(!event.isDropCompleted() && !ic.isSet()) {
                        ic.clearTarget();
                    }
                    event.consume();
                });
                ic.getCanvas().setOnDragOver(event -> {
                    if(event.getDragboard().hasImage() && !ic.isSet()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });
                ic.getCanvas().setOnDragDropped((DragEvent event) -> {
                    boolean success = false;
                    Dragboard db = event.getDragboard();
                    if(db.hasImage()) {
                        ic.clearTarget();
                        Image img = db.getImage();
                        ic.setPicture(img);
                        success = true;
                        String type = ((ListCell<String>)event.getGestureSource()).getText();
                        TopologyItem item = null;
                        switch (type){
                            case "Въезд":
                                item = new Entry();
                                break;
                            case "Выезд":
                                item = new Exit();
                                break;
                            case "ТРК":
                                item = new Dispenser();
                                break;
                            case "Касса":
                                item = new Office();
                                break;
                            case "Резервуар":
                                item = new Tank();
                                break;
                        }
                        topology.setTopologyItem(item, ic.getRowNum(), ic.getColumnNum());

                    }
                    event.setDropCompleted(success);
                    event.consume();
                });
                ic.getCanvas().setOnMouseClicked(event -> {
                    if(event.getButton() == MouseButton.SECONDARY){
                        ic.removeImage();
                        topology.setTopologyItem(null, ic.getRowNum(), ic.getColumnNum());
                        System.out.println("DEBUG");
                    }
                });
                constructorField.add(ic.getCanvas(), j, i);
            }
        }
        constructorField.setGridLinesVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        constructor.setPrefWidth(dim.getWidth() - itemList.getPrefWidth() - settings.getPrefWidth());
        constructor.setPrefHeight(dim.getHeight() - 50);
        constructorField.setLayoutX(constructor.getPrefWidth()/2 > gridWidth/2 ? constructor.getPrefWidth()/2- gridWidth/2 : 0);
        constructorField.setLayoutY(constructor.getPrefHeight()/2 > gridHeight/2 ? constructor.getPrefHeight()/2 - gridHeight/2 : 0);
        constructor.getChildren().add(constructorField);
        constructor.setPrefWidth(500);
    }
}
