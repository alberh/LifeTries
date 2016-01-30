package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.lifetries.Mappers;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.VelocityComponent;

public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(
                Family.all(
                        AnimationComponent.class,
                        VelocityComponent.class,
                        StateComponent.class
                ).get(),
                3
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animation = Mappers.animation.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);
        StateComponent state = Mappers.state.get(entity);

        if (state.isMoving) {
            switch (velocity.lastDirection) {
                case Left:
                    animation.currentAnimation = animation.animationSet.walkingLeftAnimation;
                    break;

                case Up:
                    animation.currentAnimation = animation.animationSet.walkingUpAnimation;
                    break;

                case Right:
                    animation.currentAnimation = animation.animationSet.walkingRightAnimation;
                    break;

                case Down:
                    animation.currentAnimation = animation.animationSet.walkingDownAnimation;
                    break;
            }
        } else {
            switch (velocity.lastDirection) {
                case Left:
                    animation.currentAnimation = animation.animationSet.standUpLeftAnimation;
                    break;

                case Up:
                    animation.currentAnimation = animation.animationSet.standUpFrontAnimation;
                    break;

                case Right:
                    animation.currentAnimation = animation.animationSet.standUpRightAnimation;
                    break;

                case Down:
                    animation.currentAnimation = animation.animationSet.standUpBackAmimation;
                    break;
            }
        }

        animation.currentTexture = animation.currentAnimation.getKeyFrame(deltaTime, true);
    }
}
