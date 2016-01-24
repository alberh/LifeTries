package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.lifetries.Mappers;
import com.lifetries.components.BouncingComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(
                Family.all(
                        PositionComponent.class,
                        VelocityComponent.class,
                        BouncingComponent.class,
                        StateComponent.class
                ).get(),
                2
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.position.get(entity);
        TargetPositionComponent targetPosition = Mappers.targetPosition.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);
        StateComponent state = Mappers.state.get(entity);

        if (state.hasEnergy && state.isMoving) {
            Vector2 start = new Vector2(position.x, position.y);
            Vector2 end = new Vector2(targetPosition.x, targetPosition.y);
            float distance = start.dst(end);
            float speed = velocity.speed;

            Vector2 newPosition = new Vector2(
                    start.x + deltaTime * speed * velocity.x,
                    start.y + deltaTime * speed * velocity.y
            );

            if (newPosition.dst(end) < distance) {
                position.x = newPosition.x;
                position.y = newPosition.y;
            } else {
                position.x = targetPosition.x;
                position.y = targetPosition.y;
            }
        }
    }
}
