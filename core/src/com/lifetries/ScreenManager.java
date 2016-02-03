package com.lifetries;

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
import com.lifetries.actors.EntityActorComponent;

public class ScreenManager implements EntityListener {

    private final SpriteBatch batch;

    private final Stage stage;
    public final OrthographicCamera camera;
    private final ScreenViewport viewPort;

    private final BitmapFont font;
    private final FPSLogger fps;

    public ScreenManager(LifeTries game) {
        this.batch = game.batch;

        camera = new OrthographicCamera();

        camera.translate(game.worldSize.x / 2, game.worldSize.y / 2, 0);
        viewPort = new ScreenViewport(camera);
        stage = new Stage(viewPort, batch);

        font = new BitmapFont();
        fps = new FPSLogger();
    }

    @Override
    public void entityAdded(Entity entity) {
        EntityActorComponent actor = Mappers.actor.get(entity);
        if (actor != null) {
            Gdx.app.log("!", "Añadiendo actor ");
            stage.addActor(actor);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        EntityActorComponent actor = Mappers.actor.get(entity);
        if (actor != null) {
            Gdx.app.log("!", "Eliminando actor ");
            actor.remove();
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        
        fps.log();
    }

    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
