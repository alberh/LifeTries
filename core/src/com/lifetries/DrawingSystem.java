package com.lifetries;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lifetries.Mappers;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.StateComponent;

public class DrawingSystem {

    private final Engine engine;
    private final SpriteBatch batch;
    private final Family family;
    private float elapsed;

    public DrawingSystem(Engine engine, SpriteBatch batch) {
        this.engine = engine;
        this.batch = batch;
        family = Family.all(
                PositionComponent.class,
                AnimationComponent.class,
                ColorComponent.class,
                StateComponent.class
        ).get();
        elapsed = 0;
    }

    public void draw(float deltaTime) {
        elapsed += deltaTime;

        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        for (Entity entity : entities) {
            PositionComponent position = Mappers.position.get(entity);
            AnimationComponent ac = Mappers.animation.get(entity);
            ColorComponent color = Mappers.color.get(entity);
            final int size = 20;

            batch.setColor(color.r, color.g, color.b, color.a);
            batch.draw(
                    ac.currentTexture,
                    position.x,
                    position.y,
                    size,
                    size
            );
        }
    }
}
