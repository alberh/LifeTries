package com.lifetries;

import com.lifetries.assets.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lifetries.entities.LifeBeingEntity;
import com.lifetries.systems.AnimationSystem;
import com.lifetries.systems.EnergySystem;
import com.lifetries.systems.MovementSystem;
import com.lifetries.systems.NewTargetSystem;
import com.lifetries.systems.StateSystem;

public class LifeTries extends ApplicationAdapter {

    public static LifeTries game;
    
    // Front-end
    public SpriteBatch batch;
    public ScreenManager screenManager;

    // Back-end
    public Vector2 worldSize;
    public Engine engine;
    public InputManager inputManager;

    @Override
    public void create() {
        game = this;
        
        batch = new SpriteBatch();
        engine = new Engine();
        worldSize = new Vector2(2000, 2000);
        screenManager = new ScreenManager();
        inputManager = new InputManager();

        engine.addSystem(new StateSystem());
        engine.addSystem(new NewTargetSystem(worldSize));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new EnergySystem());
        engine.addEntityListener(screenManager);

        Gdx.graphics.setTitle("Life Tries");
        Gdx.graphics.setVSync(true);
        
        Assets.load();
        generateLife();
    }

    private void generateLife() {
        while (engine.getEntities().size() < 300) {
            engine.addEntity(new LifeBeingEntity(
                    MathUtils.random() * worldSize.x,
                    MathUtils.random() * worldSize.y,
                    MathUtils.randomBoolean()
            ));
        }
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        inputManager.update(deltaTime);
        engine.update(deltaTime);
        screenManager.update();
        //screenManager.draw();
    }

    @Override
    public void resize(int width, int height) {
        screenManager.resize(width, height);
    }

    @Override
    public void dispose() {
        screenManager.dispose();
        // ya llamado por stage.dispose()
        // batch.dispose();
        Assets.dispose();
    }
}
