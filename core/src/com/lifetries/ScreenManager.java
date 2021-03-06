package com.lifetries;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lifetries.assets.Assets;
import com.lifetries.components.ActorComponent;

public class ScreenManager implements EntityListener {

    private final SpriteBatch batch;

    private final Stage stage;
    public final OrthographicCamera camera;
    private final ScreenViewport viewPort;

    private final BitmapFont font;
    private final FPSLogger fps;

    public ScreenManager() {
        this.batch = LifeTries.game.batch;

        camera = new OrthographicCamera();

        camera.translate(LifeTries.game.worldSize.x / 2, LifeTries.game.worldSize.y / 2, 0);
        viewPort = new ScreenViewport(camera);
        stage = new Stage(viewPort, batch);
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                LifeTries.game.inputManager.stageTouchDown(x, y, pointer, button);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                LifeTries.game.inputManager.stageTouchDragged(x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                LifeTries.game.inputManager.stageTouchUp(x, y, pointer, button);
            }
        });

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        fps = new FPSLogger();
    }

    @Override
    public void entityAdded(Entity entity) {
        ActorComponent actor = Mappers.actor.get(entity);
        if (actor != null) {
            stage.addActor(actor);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        ActorComponent actor = Mappers.actor.get(entity);
        if (actor != null) {
            actor.remove();
        }
    }

    public void update() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if (LifeTries.game.inputManager.dragging) {
            Vector2 origin = LifeTries.game.inputManager.dragOrigin;
            Vector2 end = LifeTries.game.inputManager.dragEnd;
            Assets.shapeRenderer.setProjectionMatrix(LifeTries.game.batch.getProjectionMatrix());
            Assets.shapeRenderer.setTransformMatrix(LifeTries.game.batch.getTransformMatrix());
            Assets.shapeRenderer.translate(origin.x, origin.y, 0);
            Assets.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Assets.shapeRenderer.setColor(Color.BLACK);
            Assets.shapeRenderer.rect(0, 0, end.x - origin.x, end.y - origin.y);
            Assets.shapeRenderer.end();
        }

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
