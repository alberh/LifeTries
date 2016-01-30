package com.lifetries.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lifetries.Mappers;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.entities.LifeBeingEntity;

public class LifeBeingActor extends Actor {

    private final LifeBeingEntity entity;
    private final PositionComponent position;
    private final AnimationComponent animation;
    private final ColorComponent color;

    public LifeBeingActor(LifeBeingEntity entity) {
        this.entity = entity;
        position = Mappers.position.get(entity);
        animation = Mappers.animation.get(entity);
        color = Mappers.color.get(entity);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(color.r, color.g, color.b, color.a);
        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(
                animation.currentTexture,
                position.x, position.y,
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }
}
