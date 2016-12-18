package edu.ssau.gasstation.GUI.controllers;

import edu.ssau.gasstation.DB.DBHelper;
import edu.ssau.gasstation.GUI.model.CarRecord;
import edu.ssau.gasstation.topology.TopologyItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Observable;


/**
 * Created by andrey on 18.12.16.
 */


public class mainWindowController {
    ObservableList<String> topologyItem = FXCollections.observableArrayList("Въезд", "Выезд", "ТРК", "Резервуар", "Касса");
    @FXML
    private ListView<String> itemList;
    @FXML
    private TableView constructorField;

    @FXML
    private void initialize() {
        itemList.setCellFactory(listView -> new ListCell<String>(){
            private ImageView pic;
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    pic = new ImageView(new Image(getClass().getResourceAsStream(getImageByName(name))));
                    setText(name);
                    setGraphic(pic);
                }
            }
        });
        itemList.setItems(topologyItem);
    }

    private String getImageByName(String name){
        String image = "images/";
        switch (name){
            case "Въезд":
                image += "in.png";
                break;
            case "Выезд":
                image += "out.png";
                break;
            case "ТРК":
                image += "dispenser.png";
                break;
            case "Резервуар":
                image += "tank.png";
                break;
            case "Касса":
                image += "office.png";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return image;
    }
}
