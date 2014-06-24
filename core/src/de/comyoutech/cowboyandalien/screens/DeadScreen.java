package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;
import de.comyoutech.cowboyandalien.model.DataCollector;

public class DeadScreen implements InputProcessor, Screen {

    private MyGdxGame game;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private int coinsEarned;

    private DataCollector collector;

    private CharSequence str = "YOU ARE DEAD";

    public DeadScreen(MyGdxGame game) {

        collector = DataCollector.getInstance();

        coinsEarned = collector.getTempCoins();
        collector.transferCoins();

        spriteBatch = new SpriteBatch();
        this.game = game;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font = new BitmapFont();

        spriteBatch.begin();
        font.draw(spriteBatch, str, 500, 500);

        CharSequence coins = "Coins gained: " + coinsEarned;
        font.draw(spriteBatch, coins, 500, 550);

        CharSequence allCoins = "Coins overall: " + collector.getCoins();
        font.draw(spriteBatch, allCoins, 500, 600);

        spriteBatch.end();

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
        if (keycode == Keys.ESCAPE) {
            System.exit(0);
        }
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
