package com.lifetries;

import com.lifetries.assets.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lifetries.entities.LifeBeingEntity;
import com.lifetries.systems.AnimationSystem;
import com.lifetries.systems.EnergySystem;
import com.lifetries.systems.MovementSystem;
import com.lifetries.systems.NewTargetSystem;
import com.lifetries.systems.StateSystem;

public class LifeTries extends ApplicationAdapter {

    // Front-end
    public SpriteBatch batch;
    public ScreenManager screenManager;

    // Back-end
    public Vector2 worldSize;
    public Engine engine;
    private InputManager inputManager;
    private DrawingSystem drawingSystem;

    @Override
    public void create() {
        batch = new SpriteBatch();
        engine = new Engine();
        worldSize = new Vector2(2000, 2000);
        screenManager = new ScreenManager(this);
        inputManager = new InputManager(screenManager.camera, worldSize);

        engine.addSystem(new StateSystem());
        engine.addSystem(new NewTargetSystem(worldSize));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new EnergySystem());

        Assets.load();

        generateLife();
        Gdx.graphics.setTitle("Life Tries");
        Gdx.graphics.setVSync(true);
    }

    private void generateLife() {
        while (engine.getEntities().size() < 100) {
            engine.addEntity(new LifeBeingEntity(
                    MathUtils.random() * worldSize.x,
                    MathUtils.random() * worldSize.y,
                    MathUtils.randomBoolean()
            ));
        }
    }

    public void update(float deltaTime) {
        inputManager.update(deltaTime);
        engine.update(deltaTime);
    }

    public void draw(float deltaTime) {
        screenManager.draw(deltaTime);
        
        
        /*
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawingSystem.draw(deltaTime);
        batch.end();

        fps.log();
                */
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
        Assets.dispose();
    }
}
