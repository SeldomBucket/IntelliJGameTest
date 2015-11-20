package com.testing.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Input;

public class IntelliJGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture dropImage, bucketImage;
	private OrthographicCamera camera;
	private Bucket bucket;
	private BitmapFont xVal, yVal, fps;
    TiledMap tiledmap;
    OrthogonalTiledMapRendererWithSprites tiledMapRenderer;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		dropImage = new Texture("droplet.png");
		bucketImage = new Texture("bucket.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		bucket = new Bucket();
		xVal = new BitmapFont();
		xVal.setColor(Color.RED);
		yVal = new BitmapFont();
		yVal.setColor(Color.GREEN);
		fps = new BitmapFont();
		fps.setColor(Color.WHITE);
        tiledmap = new TmxMapLoader().load("crapmap.tmx");
        tiledMapRenderer = new  OrthogonalTiledMapRendererWithSprites(tiledmap);
        tiledMapRenderer.addSprite(bucket);
	}

	@Override
	public void render () {
        float x, y , w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		xVal.draw(batch, "X Velocity: " + String.valueOf(bucket.getXVelocity()), Gdx.graphics.getWidth()/2-60, 300);
		yVal.draw(batch, "Y Velocity: " + String.valueOf(bucket.getYVelocity()), Gdx.graphics.getWidth()/2-60, 200);
		fps.draw(batch, String.valueOf(bucket.tileLayer), camera.position.x-300, camera.position.y+240);
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
        bucket.movement();
		batch.end();

	}



}