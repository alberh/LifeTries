package com.lifetries.assets;

import com.badlogic.gdx.graphics.Texture;

public abstract class Assets {

    public static Texture lifeBeingTexture;
    public static LifeBeingAssets lifeBeing;

    public static void load() {
        lifeBeingTexture = new Texture("lifebeing.png");
        lifeBeing = new LifeBeingAssets();
        lifeBeing.load();
    }

    public static void dispose() {
        lifeBeing.dispose();
    }
}
