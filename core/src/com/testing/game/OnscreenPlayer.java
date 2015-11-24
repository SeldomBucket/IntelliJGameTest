package com.testing.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.omg.CORBA.PUBLIC_MEMBER;

public class OnscreenPlayer extends DrawableOnscreenObject{
    private float xVelocity, yVelocity;
    private float maxVelocity = 500, accel = 3000, decel = 2000;
    float w = 1024;
    float h = 1024;
    public OnscreenPlayer(){
        xVelocity = 0;
        yVelocity = 0;
        Sprite tempSprite;
        tempSprite = getSprite();
        tempSprite.setBounds(800 / 2 - 64 / 2,20,64,64);
        tempSprite.setRegion(new Texture(Gdx.files.internal("H:\\IntelliJGameTest\\core\\assets\\bucket.png")));
        setSprite(tempSprite);
    }
    public float getXVelocity(){
        return xVelocity;
    }
    public void changeXVelocity(float velocity){
        xVelocity += velocity;
        if (xVelocity > maxVelocity) xVelocity = maxVelocity;
        if (xVelocity < -maxVelocity) xVelocity = -maxVelocity;
    }
    public void setXVelocity(float velocity){
        xVelocity = velocity;
        if (xVelocity > maxVelocity) xVelocity = maxVelocity;
        if (xVelocity < -maxVelocity) xVelocity = -maxVelocity;
    }
    public float getYVelocity(){
        return yVelocity;
    }
    public void changeYVelocity(float velocity){
        yVelocity += velocity;
        if (yVelocity > maxVelocity) yVelocity = maxVelocity;
        if (yVelocity < -maxVelocity) yVelocity = -maxVelocity;
    }
    public void setYVelocity(float velocity){
        yVelocity = velocity;
        if (yVelocity > maxVelocity) yVelocity = maxVelocity;
        if (yVelocity < -maxVelocity) yVelocity = -maxVelocity;
    }
    public float getX(){
        return getSprite().getX();
    }
    public float getY(){
        return getSprite().getY();
    }
    public void setX(float setVal){
        getSprite().setX(setVal);
    }
    public void setY(float setVal){
        getSprite().setY(setVal);
    }
    public void Left() {
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            decelXToStop();
        } else {
            changeXVelocity(accel*Gdx.graphics.getDeltaTime());
            checkEdgeCollision();
        }
    }
    public void Right(){
        changeXVelocity((-accel)*Gdx.graphics.getDeltaTime());
        checkEdgeCollision();
    }
    public void Up() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            decelYToStop();
        } else {
            changeYVelocity(accel * Gdx.graphics.getDeltaTime());
            checkEdgeCollision();
        }
    }
    public void Down() {
        changeYVelocity((-accel) * Gdx.graphics.getDeltaTime());
        checkEdgeCollision();
    }
    public void movement(){
        setX(getX() + xVelocity*Gdx.graphics.getDeltaTime());
        setY(getY() + yVelocity*Gdx.graphics.getDeltaTime());
        setTileLayer((int)(17-((getY()-40)/64)));
    }
    private void checkEdgeCollision(){
        if(getX() < 0) {setX(0); setXVelocity(0);}
        if(getX() > w - 64) {setX(w-64); setXVelocity(0);}
        if(getY() < 0) {setY(0); setYVelocity(0);}
        if(getY() > h - 64) {setY(h-64); setYVelocity(0);}
    }
    public void decelXToStop(){
        if (getXVelocity() > 0){
            if (getXVelocity() - decel*Gdx.graphics.getDeltaTime() < 0){
                setXVelocity(0);
            }else{
                changeXVelocity(-decel*Gdx.graphics.getDeltaTime());
            }
        }
        if (getXVelocity() < 0){
            if (getXVelocity() + decel*Gdx.graphics.getDeltaTime() > 0){
                setXVelocity(0);
            }else{
                changeXVelocity(decel*Gdx.graphics.getDeltaTime());
            }
        }
    }
    public void decelYToStop(){
        if (getYVelocity() > 0){
            if (getYVelocity() - decel*Gdx.graphics.getDeltaTime() < 0){
                setYVelocity(0);
            }else{
                changeYVelocity(-decel*Gdx.graphics.getDeltaTime());
            }
        }
        if (getYVelocity() < 0){
            if (getYVelocity() + decel*Gdx.graphics.getDeltaTime() > 0){
                setYVelocity(0);
            }else{
                changeYVelocity(decel*Gdx.graphics.getDeltaTime());
            }
        }
    }
}