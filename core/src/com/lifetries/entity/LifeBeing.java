package com.lifetries.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.Mappers;
import com.lifetries.component.BouncingComponent;
import com.lifetries.component.ColorComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.TextureComponent;
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

        TextureComponent tc = new TextureComponent();
        tc.img = new Texture("lifebeing.png");
        add(tc);

        add(new VelocityComponent());
        add(new BouncingComponent());
        add(new TargetPositionComponent());
        ColorComponent cc = new ColorComponent();
        cc.r = MathUtils.random();
        cc.g = MathUtils.random();
        cc.b = MathUtils.random();
        add(cc);

        entityNumber = LifeBeing.entities++;
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        PositionComponent position = Mappers.position.get(this);
        TextureComponent texture = Mappers.texture.get(this);
        ColorComponent color = Mappers.color.get(this);
        final int size = 5;

        batch.setColor(color.r, color.g, color.b, color.a);
        batch.draw(texture.img, position.x, position.y, size, size);
        /*
        TargetPositionComponent targetPosition = Mappers.targetPosition.get(this);
        batch.draw(texture.img, targetPosition.x, targetPosition.y, size, size);
        font.setColor(Color.BLACK);
        font.draw(batch, "" + entityNumber, position.x - 2, position.y + 18);
        font.draw(batch, "" + entityNumber, targetPosition.x - 2, targetPosition.y + 18);*/
    }
}
