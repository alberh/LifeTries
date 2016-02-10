package com.lifetries;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.VelocityComponent;
import com.lifetries.systems.NewTargetSystem;

public class InputManager {

    public float CAMERA_SPEED = 400;
    public float ZOOM_SPEED = 1.5f;
    public float ZOOM_MAX = 0.1f;

    private final OrthographicCamera camera;
    private final Vector2 worldSize;

    private int screenWidth = 1024;
    private int screenHeight = 600;

    public boolean dragging;
    public Vector2 dragOrigin;
    public Vector2 dragEnd;

    public InputManager() {
        camera = LifeTries.game.screen.camera;
        worldSize = LifeTries.game.worldSize;
        dragging = false;
        dragOrigin = new Vector2();
        dragEnd = new Vector2();
    }

    public void entityTouchDown(Entity entity) {
        LifeTries.game.selection.selectOne(entity);
    }

    public void entityTouchDragged(Entity entity) {

    }

    public void entityTouchUp(Entity entity) {
        // code goes here
    }

    public void stageTouchDown(float x, float y, int pointer, int button) {
        Array<Entity> selected = LifeTries.game.selection.selected;
        Entity entity;
        StateComponent state;

        if (button == Buttons.LEFT) {
            for (int i = 0; i < selected.size; i++) {
                entity = selected.get(i);
                state = Mappers.state.get(entity);
                state.autoPilot = true;
            }
            LifeTries.game.selection.deselect();
            dragging = true;
            dragOrigin.x = x;
            dragOrigin.y = y;
            dragEnd.x = x;
            dragEnd.y = y;
        } else if (button == Buttons.RIGHT) {
            if (selected.size > 0) {
                PositionComponent position;
                TargetPositionComponent target;
                VelocityComponent velocity;

                for (int i = 0; i < selected.size; i++) {
                    entity = selected.get(i);
                    position = Mappers.position.get(entity);
                    target = Mappers.targetPosition.get(entity);
                    state = Mappers.state.get(entity);
                    velocity = Mappers.velocity.get(entity);

                    target.x = x;
                    target.y = y;

                    Vector2 auxV = NewTargetSystem.getVelocity(
                            new Vector2(position.x, position.y),
                            new Vector2(target.x, target.y)
                    );
                    velocity.x = auxV.x;
                    velocity.y = auxV.y;

                    state.autoPilot = false;
                }
            }
        }
    }

    public void stageTouchDragged(float x, float y, int pointer) {
        if (dragging) {
            dragEnd.x = x;
            dragEnd.y = y;
        }
    }

    public void stageTouchUp(float x, float y, int pointer, int button) {
        if (button == Buttons.LEFT) {
            dragging = false;
            dragOrigin.x = 0;
            dragOrigin.y = 0;
            dragEnd.x = 0;
            dragEnd.y = 0;
            // get and select the entities in the square
        }
    }

    public void update(float deltaTime) {
        updateCameraPosition(deltaTime);
        updateZoom(deltaTime);
        updateOptions();
    }

    private void updateCameraPosition(float deltaTime) {
        float scalar = deltaTime * CAMERA_SPEED;
        Vector3 movement = new Vector3();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movement.x = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movement.x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            movement.y = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            movement.y += -1;
        }
        movement.scl(scalar);

        Vector3 newPosition = new Vector3(camera.position).add(movement);
        if (newPosition.x < 0) {
            movement.x = -camera.position.x;
        }
        if (newPosition.x > worldSize.x) {
            movement.x = worldSize.x - camera.position.x;
        }
        if (newPosition.y < 0) {
            movement.y = -camera.position.y;
        }
        if (newPosition.y > worldSize.y) {
            movement.y = worldSize.y - camera.position.y;
        }

        camera.translate(movement);
    }

    private void updateZoom(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            camera.zoom -= ZOOM_SPEED * deltaTime;
            if (camera.zoom < ZOOM_MAX) {
                camera.zoom = ZOOM_MAX;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            camera.zoom += ZOOM_SPEED * deltaTime;
        }
    }

    private void updateOptions() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            boolean fullScreen = Gdx.graphics.isFullscreen();
            if (!fullScreen) {
                screenWidth = Gdx.graphics.getWidth();
                screenHeight = Gdx.graphics.getHeight();

                Gdx.graphics.setFullscreenMode(
                        Gdx.graphics.getDisplayMode()
                );
            } else {
                Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
            }
        }
    }
}
