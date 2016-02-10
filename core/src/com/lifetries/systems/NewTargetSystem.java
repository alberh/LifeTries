package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lifetries.Mappers;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.VelocityComponent;

public class NewTargetSystem extends IteratingSystem {
    
    private final Vector2 worldSize;
    
    public NewTargetSystem(Vector2 world) {
        super(
                Family.all(
                        PositionComponent.class,
                        TargetPositionComponent.class,
                        VelocityComponent.class,
                        StateComponent.class
                ).get(),
                1
        );
        
        this.worldSize = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.position.get(entity);
        TargetPositionComponent target = Mappers.targetPosition.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);
        StateComponent state = Mappers.state.get(entity);

        if (state.wantsToLook) {
            state.wantsToLook = false;
            
            Vector2 newCoords = getNewCoords();
            target.x = newCoords.x;
            target.y = newCoords.y;

            float deltaY = target.y - position.y;
            float deltaX = target.x - position.x;
            float angle = MathUtils.atan2(deltaY, deltaX);

            velocity.x = MathUtils.cos(angle);
            velocity.y = MathUtils.sin(angle);

            if (Math.abs(velocity.x) > Math.abs(velocity.y)) {
                if (velocity.x > 0) {
                    velocity.lastDirection = VelocityComponent.Direction.Right;
                } else {
                    velocity.lastDirection = VelocityComponent.Direction.Left;
                }
            } else if (velocity.y > 0) {
                velocity.lastDirection = VelocityComponent.Direction.Up;
            } else {
                velocity.lastDirection = VelocityComponent.Direction.Down;
            }
        }
    }
    
    public static Vector2 getVelocity(Vector2 position, Vector2 target) {
        float deltaY = target.y - position.y;
            float deltaX = target.x - position.x;
        float angle = MathUtils.atan2(deltaY, deltaX);
        return new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));
    }

    private Vector2 getNewCoords() {
        return new Vector2(
                MathUtils.random() * worldSize.x,
                MathUtils.random() * worldSize.y
        );
    }
}
