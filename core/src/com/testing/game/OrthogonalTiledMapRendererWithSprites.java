package com.testing.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
    private Sprite sprite;
    private List<OnscreenPlayer> buckets;

    public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
        super(map);
        buckets = new ArrayList<OnscreenPlayer>();
    }

    public void addSprite(OnscreenPlayer bucket){
        buckets.add(bucket);
    }

    @Override
    public void render() {
        Map<Integer,DrawableOnscreenObject> objectsToDraw = new HashMap<>();
        for(DrawableOnscreenObject thing : buckets){
            objectsToDraw.put(thing.getTileLayer(),thing);
        }
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                renderTileLayer((TiledMapTileLayer) layer);
                currentLayer++;
                if (objectsToDraw.containsKey(currentLayer)) {
                    objectsToDraw.get(currentLayer).getSprite().draw(getBatch());
                }
            }
        }
        endRender();
    }
}