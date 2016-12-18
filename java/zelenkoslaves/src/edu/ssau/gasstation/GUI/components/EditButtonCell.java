package edu.ssau.gasstation.GUI.components;

import edu.ssau.gasstation.DB.DBHelper;
import edu.ssau.gasstation.GUI.model.CarRecord;
import edu.ssau.gasstation.GUI.model.FuelRecord;
import edu.ssau.gasstation.GUI.model.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.sql.SQLException;


/**
 * Created by andrey on 05.12.16.
 */
public class EditButtonCell extends TableCell<Record, Boolean>{
    private final Button cellButton = new Button();
    private ObservableList data;

    public EditButtonCell(ObservableList data){
        this.data = data;
    }

    @Override
    public void updateItem(Boolean item, boolean empty ) {
        super.updateItem(item, empty);
        if ( empty ) {
            setGraphic( null );
            setText( null );
        }
        else {
            ImageView pic = new ImageView(new Image(getClass().getResourceAsStream("edit.png")));
            pic.setFitHeight(20);
            pic.setFitWidth(20);
            cellButton.paddingProperty().setValue(new Insets(2, 2, 2, 2));
            cellButton.graphicProperty().setValue(pic);
            cellButton.setPrefSize(17, 17);
            cellButton.setOnAction( ( ActionEvent event ) -> {
                Record current = EditButtonCell.this.getTableView().getItems().get(EditButtonCell.this.getIndex());
                DBHelper dbh = new DBHelper();
                try {
                    if(current instanceof CarRecord) {
                        dbh.updateCar(((CarRecord)current).getCarType(), ((CarRecord)current).getTankVolume(),
                                dbh.getFuelID(((CarRecord)current).getFuelType()), current.getRecordId());
                    }
                    else if(current instanceof FuelRecord){
                        dbh.updateFuel(((FuelRecord)current).getFuelName(), ((FuelRecord)current).getFuelCost(), current.getRecordId());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } );
            cellButton.setPrefSize(30.0, 30.0);
            setGraphic( cellButton );
            setText( null );
        }
    }
}
