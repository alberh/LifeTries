package com.lifetries.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.assets.Assets;
import com.lifetries.Mappers;
import com.lifetries.components.BouncingComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.VelocityComponent;

public class LifeBeing extends Entity {

    private static int entities = 0;
    private int entityNumber;

    public LifeBeing() {
        this(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, false);
    }

    public LifeBeing(float posX, float posY, boolean skinB) {
        PositionComponent ps = new PositionComponent();
        ps.x = posX;
        ps.y = posY;
        add(ps);

        add(new VelocityComponent());
        add(new BouncingComponent());
        add(new TargetPositionComponent());
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
        add(new EnergyComponent());

        entityNumber = LifeBeing.entities++;
    }
}
