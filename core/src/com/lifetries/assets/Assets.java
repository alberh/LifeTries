package com.lifetries.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lifetries.LifeTries;

public abstract class Assets {

    public static ShapeRenderer shapeRenderer;

    public static Texture lifeBeingTexture;
    public static LifeBeingAssets lifeBeing;

    public static void load() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(LifeTries.game.batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(LifeTries.game.batch.getTransformMatrix());

        lifeBeingTexture = new Texture("lifebeing.png");
        lifeBeing = new LifeBeingAssets();
        lifeBeing.load();
    }

    public static void dispose() {
        shapeRenderer.dispose();
        lifeBeing.dispose();
    }
}
