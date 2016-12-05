package edu.ssau.gasstation.GUI;

import com.sun.prism.impl.Disposer;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;

/**
 * Created by andrey on 05.12.16.
 */
public class ButtonCell extends TableCell<Disposer.Record, Boolean>{
    Image editPicture = new Image(getClass().getResource())
    Button edit;
}
