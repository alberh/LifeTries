package com.lifetries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputManager {

    public float CAMERA_SPEED = 400;
    public float ZOOM_SPEED = 1.5f;
    public float ZOOM_MAX = 0.1f;

    private final OrthographicCamera camera;
    private final Vector2 worldSize;

    private int screenWidth;
    private int screenHeight;

    public InputManager(OrthographicCamera camera, Vector2 worldSize) {
        this.camera = camera;
        this.worldSize = worldSize;
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
        /*
        if (Gdx.input.) {
            boolean fullScreen = Gdx.graphics.isFullscreen();
            if (fullScreen) {
                screenWidth = Gdx.graphics.getWidth();
                screenHeight = Gdx.graphics.getHeight();

                Gdx.graphics.setFullscreenMode(
                        Gdx.graphics.getDisplayMode()
                );
            } else {
                Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
            }
        }*/
    }
}