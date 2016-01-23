package com.lifetries.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LifeBeingAssets {

    public Texture lifeBeingTexture;
    public Animation standUpFrontAnimation;
    public Animation standUpRightAnimation;
    public Animation standUpLeftAnimation;
    public Animation standUpBackAmimation;
    public Animation walkingUpAnimation;
    public Animation walkingRightAnimation;
    public Animation walkingLeftAnimation;
    public Animation walkingDownAnimation;

    public void load() {
        lifeBeingTexture = new Texture("lifebeing.png");
        TextureRegion standUpFront0 = new TextureRegion(
                lifeBeingTexture, 0, 0, 10, 10
        );
        TextureRegion standUpFront1 = new TextureRegion(
                lifeBeingTexture, 10, 0, 10, 10
        );
        TextureRegion standUpRight0 = new TextureRegion(
                lifeBeingTexture, 0, 10, 10, 10
        );
        TextureRegion standUpRight1 = new TextureRegion(
                lifeBeingTexture, 10, 10, 10, 10
        );
        TextureRegion standUpLeft0 = new TextureRegion(
                lifeBeingTexture, 9, 10, -10, 10
        );
        TextureRegion standUpLeft1 = new TextureRegion(
                lifeBeingTexture, 19, 10, -10, 10
        );
        TextureRegion standUpBack0 = new TextureRegion(
                lifeBeingTexture, 0, 20, 10, 10
        );
        TextureRegion standUpBack1 = new TextureRegion(
                lifeBeingTexture, 10, 20, 10, 10
        );
        TextureRegion walkingFront0 = new TextureRegion(
                lifeBeingTexture, 20, 0, 10, 10
        );
        TextureRegion walkingFront1 = new TextureRegion(
                lifeBeingTexture, 30, 0, 10, 10
        );
        TextureRegion walkingRight0 = new TextureRegion(
                lifeBeingTexture, 20, 10, 10, 10
        );
        TextureRegion walkingRight1 = new TextureRegion(
                lifeBeingTexture, 30, 10, 10, 10
        );
        TextureRegion walkingLeft0 = new TextureRegion(
                lifeBeingTexture, 29, 10, -10, 10
        );
        TextureRegion walkingLeft1 = new TextureRegion(
                lifeBeingTexture, 39, 10, -10, 10
        );
        TextureRegion walkingBack0 = new TextureRegion(
                lifeBeingTexture, 20, 20, 10, 10
        );
        TextureRegion walkingBack1 = new TextureRegion(
                lifeBeingTexture, 30, 20, 10, 10
        );
        standUpFrontAnimation = new Animation(
                1, standUpFront0, standUpFront1
        );
        standUpRightAnimation = new Animation(
                1, standUpRight0, standUpRight1
        );
        standUpLeftAnimation = new Animation(
                1, standUpLeft0, standUpLeft1
        );
        standUpBackAmimation = new Animation(
                1, standUpBack0, standUpBack1
        );
        walkingUpAnimation = new Animation(
                0.1f, standUpBack0, walkingBack0, standUpBack0, walkingBack1
        );
        walkingRightAnimation = new Animation(
                0.1f, standUpRight0, walkingRight0, standUpRight0, walkingRight1
        );
        walkingLeftAnimation = new Animation(
                0.1f, standUpLeft0, walkingLeft0, standUpLeft0, walkingLeft1
        );
        walkingDownAnimation = new Animation(
                0.1f, standUpFront0, walkingFront0, standUpFront0, walkingFront1
        );
    }
    
    public void dispose() {
        lifeBeingTexture.dispose();
    }
}
