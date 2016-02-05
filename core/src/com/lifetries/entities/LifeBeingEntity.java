package com.lifetries.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.lifetries.LifeTries;
import com.lifetries.components.ActorComponent;
import com.lifetries.assets.Assets;
import com.lifetries.components.BouncingComponent;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.VelocityComponent;

public class LifeBeingEntity extends Entity {

    public LifeBeingEntity() {
        this(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, false);
    }

    public LifeBeingEntity(float posX, float posY, boolean skinB) {

        add(new EnergyComponent());
        add(new StateComponent());
        add(new VelocityComponent());
        add(new BouncingComponent());
        add(new TargetPositionComponent());

        PositionComponent ps = new PositionComponent();
        ps.x = posX;
        ps.y = posY;
        add(ps);

        ColorComponent cc = new ColorComponent();
        cc.r = MathUtils.random();
        cc.g = MathUtils.random();
        cc.b = MathUtils.random();
        add(cc);

        AnimationComponent entityAnimation = new AnimationComponent();
        if (skinB) {
            entityAnimation.animationSet = Assets.lifeBeing.skinB;
        } else {
            entityAnimation.animationSet = Assets.lifeBeing.skinA;
        }
        add(entityAnimation);

        ActorComponent actor = new ActorComponent(this);
        actor.setBounds(posX, posY, 10, 10);
        actor.setTouchable(Touchable.enabled);
        actor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Entity entity = ((ActorComponent) event.getTarget()).entity;
                LifeTries.game.inputManager.touchDown(entity);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Entity entity = ((ActorComponent) event.getTarget()).entity;
                LifeTries.game.inputManager.touchDragged(entity);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Entity entity = ((ActorComponent) event.getTarget()).entity;
                LifeTries.game.inputManager.touchUp(entity);
            }
        });
        add(actor);
    }
}
