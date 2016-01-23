package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.lifetries.Mappers;
import com.lifetries.assets.Assets;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.VelocityComponent;

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
                    ac.animation = Assets.lifeBeing.walkingLeftAnimation;
                    break;

                case Up:
                    ac.animation = Assets.lifeBeing.walkingUpAnimation;
                    break;

                case Right:
                    ac.animation = Assets.lifeBeing.walkingRightAnimation;
                    break;

                case Down:
                    ac.animation = Assets.lifeBeing.walkingDownAnimation;
                    break;
            }
        } else {
            switch (velocity.lastDirection) {
                case Left:
                    ac.animation = Assets.lifeBeing.standUpLeftAnimation;
                    break;

                case Up:
                    ac.animation = Assets.lifeBeing.standUpFrontAnimation;
                    break;

                case Right:
                    ac.animation = Assets.lifeBeing.standUpRightAnimation;
                    break;

                case Down:
                    ac.animation = Assets.lifeBeing.standUpBackAmimation;
                    break;
            }
        }
    }
}
