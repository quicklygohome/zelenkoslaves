package edu.ssau.gasstation.GUI.components;

import edu.ssau.gasstation.GUI.model.CarRecord;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


/**
 * Created by andrey on 05.12.16.
 */
public class EditButtonCell extends TableCell<CarRecord, Boolean>{
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
                /*CarRecord currentCar = (CarRecord) EditButtonCell.this.getTableView().getItems().get(EditButtonCell.this.getIndex());
                data.remove(currentCar);*/
                //todo проверка и отправка изменений в БД
                data.add(new CarRecord(2, "fa", "dsa", 10.0));
            } );
            cellButton.setPrefSize(30.0, 30.0);
            setGraphic( cellButton );
            setText( null );
        }
    }
}
