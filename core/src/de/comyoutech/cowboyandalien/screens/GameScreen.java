package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;
import de.comyoutech.cowboyandalien.controller.WorldController;
import de.comyoutech.cowboyandalien.model.EntityStore;
import de.comyoutech.cowboyandalien.view.WorldRenderer;

/**
 * Der Screen der das Spiel repräsentiert.
 * 
 * @author BrookZ
 * 
 */
public class GameScreen implements Screen, InputProcessor {

    private WorldRenderer renderer;

    private WorldController controller;

    private MyGdxGame game;

    private int width, height;

    public GameScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        EntityStore.setUp();
        renderer = new WorldRenderer();
        controller = new WorldController(game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT) {
            controller.leftPressed();
        }
        if (keycode == Keys.RIGHT) {
            controller.rightPressed();
        }
        if (keycode == Keys.SPACE) {
            controller.jumpPressed();
        }
        if (keycode == Keys.X) {
            controller.firePressed();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.LEFT) {
            controller.leftReleased();
        }
        if (keycode == Keys.RIGHT) {
            controller.rightReleased();
        }
        if (keycode == Keys.SPACE) {
            controller.jumpReleased();
        }
        if (keycode == Keys.X) {
            controller.fireReleased();
        }
        if (keycode == Keys.ESCAPE) {
            System.exit(0);
        }
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (!Gdx.app.getType().equals(ApplicationType.Android)) {
            return false;
        }
        if ((x < (width / 2)) && (y > (height / 2))) {
            controller.leftPressed();
        }
        if ((x > (width / 2)) && (y > (height / 2))) {
            controller.rightPressed();
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (!Gdx.app.getType().equals(ApplicationType.Android)) {
            return false;
        }
        if ((x < (width / 2)) && (y > (height / 2))) {
            controller.leftReleased();
        }
        if ((x > (width / 2)) && (y > (height / 2))) {
            controller.rightReleased();
        }
        return true;
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
