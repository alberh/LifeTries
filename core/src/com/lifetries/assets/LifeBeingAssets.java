package com.lifetries.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LifeBeingAssets {

    public Texture texture;
    public LifeBeingAnimations skinA;
    public LifeBeingAnimations skinB;

    private void loadSkinA() {
        skinA = new LifeBeingAnimations();

        TextureRegion standUpFront0 = new TextureRegion(
                texture, 0, 0, 10, 10
        );
        TextureRegion standUpFront1 = new TextureRegion(
                texture, 10, 0, 10, 10
        );
        TextureRegion standUpRight0 = new TextureRegion(
                texture, 0, 10, 10, 10
        );
        TextureRegion standUpRight1 = new TextureRegion(
                texture, 10, 10, 10, 10
        );
        TextureRegion standUpLeft0 = new TextureRegion(
                texture, 9, 10, -10, 10
        );
        TextureRegion standUpLeft1 = new TextureRegion(
                texture, 19, 10, -10, 10
        );
        TextureRegion standUpBack0 = new TextureRegion(
                texture, 0, 20, 10, 10
        );
        TextureRegion standUpBack1 = new TextureRegion(
                texture, 10, 20, 10, 10
        );
        TextureRegion walkingFront0 = new TextureRegion(
                texture, 20, 0, 10, 10
        );
        TextureRegion walkingFront1 = new TextureRegion(
                texture, 30, 0, 10, 10
        );
        TextureRegion walkingRight0 = new TextureRegion(
                texture, 20, 10, 10, 10
        );
        TextureRegion walkingRight1 = new TextureRegion(
                texture, 30, 10, 10, 10
        );
        TextureRegion walkingLeft0 = new TextureRegion(
                texture, 29, 10, -10, 10
        );
        TextureRegion walkingLeft1 = new TextureRegion(
                texture, 39, 10, -10, 10
        );
        TextureRegion walkingBack0 = new TextureRegion(
                texture, 20, 20, 10, 10
        );
        TextureRegion walkingBack1 = new TextureRegion(
                texture, 30, 20, 10, 10
        );
        skinA.standUpFrontAnimation = new Animation(
                1, standUpFront0, standUpFront1
        );
        skinA.standUpRightAnimation = new Animation(
                1, standUpRight0, standUpRight1
        );
        skinA.standUpLeftAnimation = new Animation(
                1, standUpLeft0, standUpLeft1
        );
        skinA.standUpBackAmimation = new Animation(
                1, standUpBack0, standUpBack1
        );
        skinA.walkingUpAnimation = new Animation(
                0.1f, standUpBack0, walkingBack0, standUpBack0, walkingBack1
        );
        skinA.walkingRightAnimation = new Animation(
                0.1f, standUpRight0, walkingRight0, standUpRight0, walkingRight1
        );
        skinA.walkingLeftAnimation = new Animation(
                0.1f, standUpLeft0, walkingLeft0, standUpLeft0, walkingLeft1
        );
        skinA.walkingDownAnimation = new Animation(
                0.1f, standUpFront0, walkingFront0, standUpFront0, walkingFront1
        );
    }

    private void loadSkinB() {
        skinB = new LifeBeingAnimations();

        TextureRegion standUpFront0 = new TextureRegion(
                texture, 0, 30, 10, 10
        );
        TextureRegion standUpFront1 = new TextureRegion(
                texture, 10, 30, 10, 10
        );
        TextureRegion standUpRight0 = new TextureRegion(
                texture, 0, 40, 10, 10
        );
        TextureRegion standUpRight1 = new TextureRegion(
                texture, 10, 40, 10, 10
        );
        TextureRegion standUpLeft0 = new TextureRegion(
                texture, 9, 40, -10, 10
        );
        TextureRegion standUpLeft1 = new TextureRegion(
                texture, 19, 40, -10, 10
        );
        TextureRegion standUpBack0 = new TextureRegion(
                texture, 0, 50, 10, 10
        );
        TextureRegion standUpBack1 = new TextureRegion(
                texture, 10, 50, 10, 10
        );
        TextureRegion walkingFront0 = new TextureRegion(
                texture, 20, 30, 10, 10
        );
        TextureRegion walkingFront1 = new TextureRegion(
                texture, 30, 30, 10, 10
        );
        TextureRegion walkingRight0 = new TextureRegion(
                texture, 20, 40, 10, 10
        );
        TextureRegion walkingRight1 = new TextureRegion(
                texture, 30, 40, 10, 10
        );
        TextureRegion walkingLeft0 = new TextureRegion(
                texture, 29, 40, -10, 10
        );
        TextureRegion walkingLeft1 = new TextureRegion(
                texture, 39, 40, -10, 10
        );
        TextureRegion walkingBack0 = new TextureRegion(
                texture, 20, 50, 10, 10
        );
        TextureRegion walkingBack1 = new TextureRegion(
                texture, 30, 50, 10, 10
        );
        skinB.standUpFrontAnimation = new Animation(
                1, standUpFront0, standUpFront1
        );
        skinB.standUpRightAnimation = new Animation(
                1, standUpRight0, standUpRight1
        );
        skinB.standUpLeftAnimation = new Animation(
                1, standUpLeft0, standUpLeft1
        );
        skinB.standUpBackAmimation = new Animation(
                1, standUpBack0, standUpBack1
        );
        skinB.walkingUpAnimation = new Animation(
                0.1f, standUpBack0, walkingBack0, standUpBack0, walkingBack1
        );
        skinB.walkingRightAnimation = new Animation(
                0.1f, standUpRight0, walkingRight0, standUpRight0, walkingRight1
        );
        skinB.walkingLeftAnimation = new Animation(
                0.1f, standUpLeft0, walkingLeft0, standUpLeft0, walkingLeft1
        );
        skinB.walkingDownAnimation = new Animation(
                0.1f, standUpFront0, walkingFront0, standUpFront0, walkingFront1
        );
    }

    public void load() {
        texture = Assets.lifeBeingTexture;
        loadSkinA();
        loadSkinB();

    }

    public void dispose() {
        texture.dispose();
    }
}
