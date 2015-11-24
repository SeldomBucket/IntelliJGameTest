package com.testing.game;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Created by SeldomBucket on 23-Nov-15.
 */
public class DrawableOnscreenObject {
    public Sprite sprite;
    private int tileLayer;
    public DrawableOnscreenObject(){
        sprite = new Sprite();
        sprite.setX(0);
        sprite.setY(0);
    }
    public Sprite getSprite(){
        return sprite;
    }
    public void setSprite(Sprite newSprite){
        sprite = newSprite;
    }
    public int getTileLayer(){
        return tileLayer;
    }
    public void setTileLayer(int newLayer){
        tileLayer = newLayer;
    }
}
