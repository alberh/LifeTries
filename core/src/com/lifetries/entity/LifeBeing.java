package com.lifetries.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.Assets;
import com.lifetries.Mappers;
import com.lifetries.component.BouncingComponent;
import com.lifetries.component.ColorComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.AnimationComponent;
import com.lifetries.component.VelocityComponent;

public class LifeBeing extends Entity {

    private static int entities = 0;
    private int entityNumber;

    public LifeBeing() {
        this(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public LifeBeing(float posX, float posY) {
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
        add(new AnimationComponent());

        entityNumber = LifeBeing.entities++;
    }
}
