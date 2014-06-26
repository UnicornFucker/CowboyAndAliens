package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;
import de.comyoutech.cowboyandalien.controller.MyGdxGame.GameState;
import de.comyoutech.cowboyandalien.controller.WorldController;
import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.view.WorldRenderer;

/**
 * Der Screen der das Spiel repräsentiert.
 * 
 * @author BrookZ
 * 
 */
public class GameScreen extends AbstractScreen {

    /**
     * Renderer-object.
     */
    private WorldRenderer renderer;

    /**
     * Controller-object.
     */
    private WorldController controller;

    /**
     * Width and height of the level.
     */
    private int width, height;

    public GameScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        Assets.soundBackground.loop();
        renderer = new WorldRenderer();
        controller = new WorldController(game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        if (delta < 0.1) {
            controller.update(delta);
            renderer.render();
        }
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
        if (keycode == Keys.P) {
            if (game.gameState == GameState.RUN) {
                game.gameState = GameState.PAUSE;
            }
            else if (game.gameState == GameState.PAUSE) {
                game.gameState = GameState.PAUSE;
            }
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
        game.gameState = GameState.PAUSE;
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
