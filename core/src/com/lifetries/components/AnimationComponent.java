package com.lifetries.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.lifetries.assets.LifeBeingAnimations;

public class AnimationComponent implements Component {
    
    public LifeBeingAnimations animationSet = null;
    public Animation currentAnimation = null;
}
