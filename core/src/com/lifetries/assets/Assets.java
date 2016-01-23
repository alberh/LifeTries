package com.lifetries.assets;

public abstract class Assets {

    public static LifeBeingAssets lifeBeing;

    public static void load() {
        lifeBeing = new LifeBeingAssets();
        lifeBeing.load();
    }
    
    public static void dispose() {
        lifeBeing.dispose();
    }
}
