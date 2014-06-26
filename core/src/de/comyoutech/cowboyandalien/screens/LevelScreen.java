package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;

/**
 * The screen that is shown if the level is solved.
 * 
 * @author BrookZ
 * 
 */
public class LevelScreen extends AbstractScreen {

    /**
     * The font that is drawn on the screen.
     */
    private BitmapFont font;

    /**
     * The String that tells you that you solved the level.
     */
    private CharSequence str = "You Solved the Level!!!!";

    public LevelScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font = new BitmapFont();

        font.draw(batch, str, 500, 500);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.setMenuScreen();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {

        return false;
    }

}
