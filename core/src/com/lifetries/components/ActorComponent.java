package com.lifetries.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lifetries.Mappers;
import com.lifetries.assets.Assets;

public class ActorComponent extends Actor implements Component {

    public final Entity entity;

    public ActorComponent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //PositionComponent position = Mappers.position.get(entity);
        AnimationComponent animation = Mappers.animation.get(entity);
        ColorComponent color = Mappers.color.get(entity);
        StateComponent state = Mappers.state.get(entity);

        batch.setColor(color.r, color.g, color.b, color.a);
        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(
                animation.currentTexture,
                getX(), getY(),
                getWidth(), getHeight()
        );

        if (state.isSelected) {
            // Draws a cool cyan hitbox
            batch.end();

            Assets.shapeRenderer.translate(getX(), getY(), 0);
            Assets.shapeRenderer.begin(ShapeType.Line);
            Assets.shapeRenderer.setColor(Color.CYAN);
            Assets.shapeRenderer.rect(
                    0, 0,
                    animation.currentTexture.getRegionWidth(),
                    animation.currentTexture.getRegionHeight()
            );
            Assets.shapeRenderer.end();

            batch.begin();
        }
    }
}
