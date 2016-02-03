package com.lifetries;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lifetries.actors.EntityActor;
import com.lifetries.entities.LifeBeingEntity;
import com.sun.javafx.scene.SceneUtils;
import javafx.geometry.Point2D;

public class ScreenManager implements EntityListener {

    private final Engine engine;
    private final SpriteBatch batch;

    private final Stage stage;
    public final OrthographicCamera camera;
    private final ScreenViewport viewPort;

    private final BitmapFont font;
    private final FPSLogger fps;

    public ScreenManager(LifeTries game) {
        this.engine = game.engine;
        this.batch = game.batch;

        camera = new OrthographicCamera();

        camera.translate(game.worldSize.x / 2, game.worldSize.y / 2, 0);
        viewPort = new ScreenViewport(camera);
        stage = new Stage();

        font = new BitmapFont();
        fps = new FPSLogger();
    }

    @Override
    public void entityAdded(Entity entity) {
        LifeBeingEntity lbe = (LifeBeingEntity) entity;
        EntityActor actor = new EntityActor(lbe);
        stage.addActor(actor);
    }

    @Override
    public void entityRemoved(Entity entity) {
        
    }

    public void draw(float deltaTime) {
        stage.draw();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawingSystem.draw(deltaTime);
        batch.end();

        fps.log();
    }

}
