package com.lifetries.assets;

public abstract class Assets {

    public static LifeBeingAssets lifeBeing;

    public static void load() {
        lifeBeing = new LifeBeingAssets();
        LifeBeingAssets.load();
    }
    
    public static void dispose() {
        lifeBeing.dispose();
    }
}
