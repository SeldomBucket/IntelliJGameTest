package com.testing.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bucket {
    private float xVelocity, yVelocity;
    private Sprite box;
    private float maxVelocity = 500, accel = 3000, decel = 2000;
    float w = 1024;
    float h = 1024;
    int tileLayer = 0;

    public Bucket(){
        xVelocity = 0;
        yVelocity = 0;
        box = new Sprite();
        box.setBounds(800 / 2 - 64 / 2,20,64,64);
        box.setRegion(new Texture("bucket.png"));
    }
    public Sprite getBox(){
        return box;
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
        return box.getX();
    }
    public float getY(){
        return box.getY();
    }
    public void setX(float setVal){
        box.setX(setVal);
    }
    public void setY(float setVal){
        box.setY(setVal);
    }
    public void movement(){
        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)){
            decelXToStop();
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                changeXVelocity(accel*Gdx.graphics.getDeltaTime());
                checkEdgeCollision();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                changeXVelocity((-accel)*Gdx.graphics.getDeltaTime());
                checkEdgeCollision();
            }
            if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)){
                decelXToStop();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)){
            decelYToStop();
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                changeYVelocity(accel*Gdx.graphics.getDeltaTime());
                checkEdgeCollision();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                changeYVelocity((-accel)*Gdx.graphics.getDeltaTime());
                checkEdgeCollision();
            }
            if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)){
                decelYToStop();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            setXVelocity(0);
            setYVelocity(0);
        }
        checkEdgeCollision();
        setX(getX() + getXVelocity()*Gdx.graphics.getDeltaTime());
        setY(getY() + getYVelocity()*Gdx.graphics.getDeltaTime());
        tileLayer = (int)(17-((getY()-40)/64));
    }
    private void checkEdgeCollision(){
        if(getX() < 0) {setX(0); setXVelocity(0);}
        if(getX() > w - 64) {setX(w-64); setXVelocity(0);}
        if(getY() < 0) {setY(0); setYVelocity(0);}
        if(getY() > h - 64) {setY(h-64); setYVelocity(0);}
    }
    private void decelXToStop(){
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
    private void decelYToStop(){
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