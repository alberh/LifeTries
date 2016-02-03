package com.lifetries.actors;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lifetries.Mappers;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;

public class EntityActorComponent extends Actor implements Component {

    public final Entity entity;

    public EntityActorComponent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        PositionComponent position = Mappers.position.get(entity);
        AnimationComponent animation = Mappers.animation.get(entity);
        ColorComponent color = Mappers.color.get(entity);

        batch.setColor(color.r, color.g, color.b, color.a);
        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(
                animation.currentTexture,
                position.x, position.y
        );
    }
}
