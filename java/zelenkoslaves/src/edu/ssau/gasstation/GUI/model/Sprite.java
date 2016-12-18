package edu.ssau.gasstation.GUI.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Sasha on 18.12.2016.
 */
public enum Sprite {
    EMPTY("empty"),
    CAR("car"),
    ENTER("enter"),
    EXIT("exit"),
    CASH("cash"),
    TANK("tank"),
    TRK("trk");

    private BufferedImage sprite;

    Sprite(String element){
        try {
            this.sprite = ImageIO.read(new File("src/edu/ssau/gasstation/GUI/view/icons/"+element+".png"));
        } catch (Exception e){
            this.sprite = null;
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
