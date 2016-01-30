package com.lifetries.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.assets.Assets;
import com.lifetries.components.BouncingComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.VelocityComponent;

public class LifeBeingEntity extends Entity {

    public LifeBeingEntity() {
        this(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, false);
    }

    public LifeBeingEntity(float posX, float posY, boolean skinB) {
        add(new EnergyComponent());
        add(new StateComponent());
        add(new VelocityComponent());
        add(new BouncingComponent());
        add(new TargetPositionComponent());
        
        PositionComponent ps = new PositionComponent();
        ps.x = posX;
        ps.y = posY;
        add(ps);

        ColorComponent cc = new ColorComponent();
        cc.r = MathUtils.random();
        cc.g = MathUtils.random();
        cc.b = MathUtils.random();
        add(cc);
        
        AnimationComponent entityAnimation = new AnimationComponent();
        if (skinB) {
            entityAnimation.animationSet = Assets.lifeBeing.skinB;
        } else {
            entityAnimation.animationSet = Assets.lifeBeing.skinA;
        }
        add(entityAnimation);
    }
}
