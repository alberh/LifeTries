package com.lifetries.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.lifetries.Mappers;
import com.lifetries.component.BouncingComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(
                Family.all(
                        PositionComponent.class,
                        VelocityComponent.class,
                        BouncingComponent.class
                ).get(),
                1
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.position.get(entity);
        TargetPositionComponent targetPosition = Mappers.targetPosition.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);

        if (velocity.isMoving) {
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
                velocity.isMoving = false;
            }
        }
    }
}
