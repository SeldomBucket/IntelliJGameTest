package com.testing.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class IntelliJGame extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	SpriteBatch batch;
	private OrthographicCamera camera;
	private OnscreenPlayer bucket;
	private BitmapFont xVal, yVal, fps;
    TiledMap tiledmap;
    OrthogonalTiledMapRendererWithSprites tiledMapRenderer;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		bucket = new OnscreenPlayer();
		xVal = new BitmapFont();
		xVal.setColor(Color.RED);
		yVal = new BitmapFont();
		yVal.setColor(Color.GREEN);
		fps = new BitmapFont();
		fps.setColor(Color.WHITE);
        tiledmap = new TmxMapLoader().load("H:\\IntelliJGameTest\\core\\assets\\crapmap.tmx");
        tiledMapRenderer = new  OrthogonalTiledMapRendererWithSprites(tiledmap);
        tiledMapRenderer.addSprite(bucket);
        Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		xVal.draw(batch, "X Velocity: " + String.valueOf(bucket.getXVelocity()), Gdx.graphics.getWidth()/2-60, 300);
		yVal.draw(batch, "Y Velocity: " + String.valueOf(bucket.getYVelocity()), Gdx.graphics.getWidth()/2-60, 200);
		fps.draw(batch, String.valueOf(bucket.getTileLayer()), camera.position.x-300, camera.position.y+240);
        playerMovement();
        cameraMovement();
		batch.end();
	}
    boolean keyflagW,keyflagD,keyflagA,keyflagS;
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W) keyflagW = true;
        if(keycode == Input.Keys.S) keyflagS = true;
        if(keycode == Input.Keys.D) keyflagD = true;
        if(keycode == Input.Keys.A) keyflagA = true;
        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W) keyflagW = false;
        if(keycode == Input.Keys.S) keyflagS = false;
        if(keycode == Input.Keys.D) keyflagD = false;
        if(keycode == Input.Keys.A) keyflagA = false;
        return true;
    }
    public void playerMovement(){
        if(keyflagW == true) bucket.Up();
        if(keyflagA == true) bucket.Right();
        if(keyflagD == true) bucket.Left();
        if(keyflagS == true) bucket.Down();
        if(!keyflagD && !keyflagA){
            bucket.decelXToStop();
        }
        if(!keyflagW && !keyflagS){
            bucket.decelYToStop();
        }
        bucket.movement();
    }
    public void cameraMovement(){
        float x, y , w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        if (bucket.getY() > 1024 - (Gdx.graphics.getHeight()/2)){
            camera.position.set(bucket.getX(), 1024 - (Gdx.graphics.getHeight()/2),0);
        }
        if (bucket.getY() > 1024 - (h/2)){
            y = (1024 - (h/2));
        }else if (bucket.getY() < (h/2)) {
            y = (h/2);
        }else{
            y = bucket.getY();
        }
        if (bucket.getX() > 1024 - (w/2)){
            x = (1024 - (w/2));
        }else if (bucket.getX() < (w/2)) {
            x = (w/2);
        }else{
            x = bucket.getX();
        }
        camera.position.set(x,y,0);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}