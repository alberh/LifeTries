package com.lifetries.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.lifetries.Assets;
import com.lifetries.Mappers;
import com.lifetries.component.AnimationComponent;
import com.lifetries.component.VelocityComponent;

public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(
                Family.all(
                        AnimationComponent.class,
                        VelocityComponent.class
                ).get(),
                2
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent ac = Mappers.animation.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);

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
    }
}
