package edu.ssau.gasstation.GUI.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Created by Sasha on 18.12.2016.
 */
public class ModelField {
    public static final int CELL_SIZE = 32; //cell size in pixels
    private static Cell[][] cells;
    private Pane pane;
    //sizes in cells!
    private int fieldSizeX;
    private int fieldSizeY;

    public ModelField(){
        this.fieldSizeX = 2;
        this.fieldSizeY = 2;
        cells = new Cell[fieldSizeX][fieldSizeY];
    }

    public ModelField(int fieldSizeX, int fieldSizeY, Pane pane){
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.pane = pane;
        cells = new Cell[fieldSizeX][fieldSizeY];
    }

    public void setFieldSizeX(int fieldSizeX) {
        this.fieldSizeX = fieldSizeX;
    }

    public void setFieldSizeY(int fieldSizeY) {
        this.fieldSizeY = fieldSizeY;
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    public int getState(int x, int y){
        return cells[x][y].getState();
    }

    public void setState(int x, int y, int state){
        cells[x][y].setState(state);
    }

    public void init(){
        pane.setPrefSize(fieldSizeX*CELL_SIZE, fieldSizeY*CELL_SIZE);
        for (int i = 0; i < fieldSizeX; i++){
            for (int j=0; j<fieldSizeY; j++){
                cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE, 0);
            }
        }
    }

    public void draw(){
        for (Cell[] line : cells){
            for (Cell cell : line){
                drawElment(cell);
            }
        }
    }

    private static void drawElment(Cell elem){
        if (elem.getSprite() == null) return;
        //отрисовка

    }
}
