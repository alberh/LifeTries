package com.lifetries.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.Assets;
import com.lifetries.Mappers;
import com.lifetries.component.BouncingComponent;
import com.lifetries.component.ColorComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.AnimationComponent;
import com.lifetries.component.VelocityComponent;

public class LifeBeing extends Entity {

    private static int entities = 0;
    private int entityNumber;

    public LifeBeing() {
        this(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public LifeBeing(float posX, float posY) {
        PositionComponent ps = new PositionComponent();
        ps.x = posX;
        ps.y = posY;
        add(ps);

        add(new VelocityComponent());
        add(new BouncingComponent());
        add(new TargetPositionComponent());
        ColorComponent cc = new ColorComponent();
        cc.r = MathUtils.random();
        cc.g = MathUtils.random();
        cc.b = MathUtils.random();
        add(cc);
        add(new AnimationComponent());

        entityNumber = LifeBeing.entities++;
    }

    public void draw(SpriteBatch batch, BitmapFont font, float elapsed) {
        PositionComponent position = Mappers.position.get(this);
        VelocityComponent velocity = Mappers.velocity.get(this);
        AnimationComponent ac = Mappers.texture.get(this);
        ColorComponent color = Mappers.color.get(this);
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
