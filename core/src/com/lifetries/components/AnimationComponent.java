package com.lifetries.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lifetries.assets.LifeBeingAnimations;

public class AnimationComponent implements Component {
    
    public LifeBeingAnimations animationSet = null;
    public Animation currentAnimation = null;
    public TextureRegion currentTexture = null;
}
