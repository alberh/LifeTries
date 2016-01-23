package com.lifetries;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lifetries.entity.LifeBeing;
import com.lifetries.system.AnimationSystem;
import com.lifetries.system.DrawingSystem;
import com.lifetries.system.MovementSystem;
import com.lifetries.system.NewTargetSystem;

public class LifeTries extends ApplicationAdapter {

    private SpriteBatch batch;
    private Engine engine;
    private DrawingSystem drawingSystem;

    private OrthographicCamera camera;
    private ScreenViewport viewPort;
    private Vector2 worldSize;

    private BitmapFont font;
    private FPSLogger fps;

    @Override
    public void create() {
        batch = new SpriteBatch();
        engine = new Engine();
        drawingSystem = new DrawingSystem(engine, batch);

        worldSize = new Vector2(2000, 2000);

        camera = new OrthographicCamera();
        camera.translate(worldSize.x / 2, worldSize.y / 2, 0);
        viewPort = new ScreenViewport(camera);

        font = new BitmapFont();
        fps = new FPSLogger();

        engine.addSystem(new NewTargetSystem(worldSize));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new AnimationSystem());

        Assets.load();

        generateLife();
        Gdx.graphics.setTitle("Life Tries");
        Gdx.graphics.setVSync(true);
    }

    private void generateLife() {
        while (engine.getEntities().size() < 2000) {
            engine.addEntity(new LifeBeing(worldSize.x / 2, worldSize.y / 2));
        }
    }

    public void update(float deltaTime) {
        engine.update(deltaTime);

        float cameraSpeed = 400;
        float scalar = deltaTime * cameraSpeed;
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
        
        // zoom
        float zoomSpeed = 1.5f;
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            camera.zoom -= zoomSpeed * deltaTime;
            if (camera.zoom < 0.1) {
                camera.zoom = 0.1f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            camera.zoom += zoomSpeed * deltaTime;
        }
    }

    public void draw(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawingSystem.draw(deltaTime);
        batch.end();

        fps.log();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);
        draw(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
