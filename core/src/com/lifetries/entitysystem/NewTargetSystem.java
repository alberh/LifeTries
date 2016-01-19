package com.lifetries.entitysystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lifetries.LifeTries;
import com.lifetries.Mappers;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.VelocityComponent;

public class NewTargetSystem extends IteratingSystem {

    private LifeTries game;
    
    public NewTargetSystem(LifeTries game) {
        super(
                Family.all(
                        PositionComponent.class,
                        TargetPositionComponent.class,
                        VelocityComponent.class
                ).get()
        );
        
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.position.get(entity);
        TargetPositionComponent target = Mappers.targetPosition.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);

        if (!velocity.isMoving) {
            Vector2 newCoords = NewTargetSystem.getNewCoords();
            target.x = newCoords.x;
            target.y = newCoords.y;

            float deltaY = target.y - position.y;
            float deltaX = target.x - position.x;
            float angleD = MathUtils.atan2(deltaY, deltaX);// * 180 / MathUtils.PI * MathUtils.radiansToDegrees;

            velocity.x = MathUtils.cos(angleD);
            velocity.y = MathUtils.sin(angleD);

            velocity.isMoving = true;
        }
    }

    public static Vector2 getNewCoords() {
        return new Vector2(
                10 + MathUtils.random() * (float) (Gdx.graphics.getWidth() - 30),
                10 + MathUtils.random() * (float) (Gdx.graphics.getHeight() - 30)
        );
    }
}
