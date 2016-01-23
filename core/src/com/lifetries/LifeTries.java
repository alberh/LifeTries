package com.lifetries;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lifetries.entity.LifeBeing;
import com.lifetries.entitysystem.DrawingSystem;
import com.lifetries.entitysystem.MovementSystem;
import com.lifetries.entitysystem.NewTargetSystem;

public class LifeTries extends ApplicationAdapter {

    private SpriteBatch batch;
    private Engine engine;
    private DrawingSystem drawingSystem;

    private Camera camera;
    private ScreenViewport viewPort;

    private BitmapFont font;
    private FPSLogger fps;
    
    private float deltaTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        engine = new Engine();
        drawingSystem = new DrawingSystem(engine, batch);
        
        camera = new OrthographicCamera();
        viewPort = new ScreenViewport(camera);
        font = new BitmapFont();
        fps = new FPSLogger();
        deltaTime = 0;

        engine.addSystem(new MovementSystem(this));
        engine.addSystem(new NewTargetSystem(this));

        Assets.load();
        
        generateLife();
        Gdx.graphics.setTitle("Life Tries");
        Gdx.graphics.setVSync(true);
    }

    private void generateLife() {
        while (engine.getEntities().size() < 2000) {
            engine.addEntity(new LifeBeing());
        }
    }

    public void update() {
        engine.update(Gdx.graphics.getDeltaTime());
    }

    public void draw() {
        deltaTime += Gdx.graphics.getDeltaTime();
        
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();

        drawingSystem.draw(deltaTime);

        batch.end();

        fps.log();
    }

    @Override
    public void render() {
        update();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
