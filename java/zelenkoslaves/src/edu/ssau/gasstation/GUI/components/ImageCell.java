package edu.ssau.gasstation.GUI.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created by andrey on 19.12.16.
 */
public class ImageCell {
    private Canvas c;
    private GraphicsContext gc;
    private boolean isSet;
    private int i;
    private int j;

    public ImageCell(int rowNum, int columnNum, double width, double height){
        c = new Canvas(width, height);
        gc = c.getGraphicsContext2D();
        isSet = false;
        i = rowNum;
        j = columnNum;
    }

    public void setPicture(Image pic){
        gc.drawImage(pic, c.getWidth()/2 - pic.getWidth()/2, c.getHeight()/2 - pic.getHeight()/2);
        isSet = true;
    }

    public boolean isSet(){
        return isSet;
    }

    public void setTarget(){
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    public void clearTarget(){
        gc.clearRect(0, 0, c.getBaselineOffset(), c.getHeight());
    }

    public int getRowNum() {
        return i;
    }

    public int getColumnNum() {
        return j;
    }

    public Canvas getCanvas(){
        return this.c;
    }

    public void removeImage(){
        gc.clearRect(0, 0, c.getHeight(), c.getHeight());
        isSet = false;
    }
}
