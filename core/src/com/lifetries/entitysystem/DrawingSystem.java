/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifetries.entitysystem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lifetries.Assets;
import com.lifetries.Mappers;
import com.lifetries.component.AnimationComponent;
import com.lifetries.component.ColorComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.VelocityComponent;

/**
 *
 * @author Alberto García González
 */
public class DrawingSystem {

    private Engine engine;
    private SpriteBatch batch;
    private float elapsed;
    private Family family;

    public DrawingSystem(Engine engine, SpriteBatch batch) {
        this.engine = engine;
        this.batch = batch;
        elapsed = 0;
        family = Family.all(
                PositionComponent.class,
                VelocityComponent.class,
                AnimationComponent.class,
                ColorComponent.class
        ).get();
    }

    public void draw(float deltaTime) {
        elapsed += deltaTime;

        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        for (Entity entity : entities) {
            PositionComponent position = Mappers.position.get(entity);
            VelocityComponent velocity = Mappers.velocity.get(entity);
            AnimationComponent ac = Mappers.texture.get(entity);
            ColorComponent color = Mappers.color.get(entity);
            final int size = 20;

            if (velocity.isMoving) {
                switch (velocity.lastDirection) {
                    case Left:
                        ac.animation = Assets.walkingLeftAnimation;
                        break;

                    case Up:
                        ac.animation = Assets.walkingUpAnimation;
                        break;

                    case Right:
                        ac.animation = Assets.walkingRightAnimation;
                        break;

                    case Down:
                        ac.animation = Assets.walkingDownAnimation;
                        break;
                }
            } else {
                switch (velocity.lastDirection) {
                    case Left:
                        ac.animation = Assets.standUpLeftAnimation;
                        break;

                    case Up:
                        ac.animation = Assets.standUpFrontAnimation;
                        break;

                    case Right:
                        ac.animation = Assets.standUpRightAnimation;
                        break;

                    case Down:
                        ac.animation = Assets.standUpBackAmimation;
                        break;
                }
            }

            batch.setColor(color.r, color.g, color.b, color.a);
            batch.draw(
                    ac.animation.getKeyFrame(elapsed, true),
                    position.x,
                    position.y,
                    size,
                    size
            );
            /*
        TargetPositionComponent targetPosition = Mappers.targetPosition.get(this);
        batch.draw(texture.img, targetPosition.x, targetPosition.y, size, size);
        font.setColor(Color.BLACK);
        font.draw(batch, "" + entityNumber, position.x - 2, position.y + 18);
        font.draw(batch, "" + entityNumber, targetPosition.x - 2, targetPosition.y + 18);*/
        }
    }
}
