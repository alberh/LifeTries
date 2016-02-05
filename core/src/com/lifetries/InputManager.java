package com.lifetries;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lifetries.components.StateComponent;

public class InputManager {

    public float CAMERA_SPEED = 400;
    public float ZOOM_SPEED = 1.5f;
    public float ZOOM_MAX = 0.1f;

    private final OrthographicCamera camera;
    private final Vector2 worldSize;

    private int screenWidth = 1024;
    private int screenHeight = 600;

    public InputManager() {
        camera = LifeTries.game.screenManager.camera;
        worldSize = LifeTries.game.worldSize;
    }

    public void touchDown(Entity entity) {
        StateComponent state = Mappers.state.get(entity);
        state.isSelected = true;

        if (StateComponent.selectedEntity != null) {
            StateComponent lastSelectedState = Mappers.state.get(
                    StateComponent.selectedEntity
            );
            lastSelectedState.isSelected = false;
        }

        StateComponent.selectedEntity = entity;
    }

    public void touchDragged(Entity entity) {

    }

    public void touchUp(Entity entity) {
        // code goes here
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
