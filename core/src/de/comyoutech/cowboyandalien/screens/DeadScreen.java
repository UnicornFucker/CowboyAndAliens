package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;
import de.comyoutech.cowboyandalien.model.DataCollector;

/**
 * The Screen that is shown if the player dies.
 * 
 * @author Kevin
 * 
 */
public class DeadScreen extends AbstractScreen {

    /**
     * The font that is drawn.
     */
    private BitmapFont font;

    /**
     * The coins that have been earned in the last round.
     */
    private int coinsEarned;

    /**
     * DataCollector-object.
     */
    private DataCollector collector;

    /**
     * The String that tells you that you are dead.
     */
    private CharSequence str = "YOU ARE DEAD";

    /**
     * Constructor.
     * 
     * @param game
     */
    public DeadScreen(MyGdxGame game) {
        super(game);
        collector = DataCollector.getInstance();

        coinsEarned = collector.getTempCoins();
        collector.transferCoins();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        font = new BitmapFont();

        batch.begin();
        font.draw(batch, str, 500, 400);

        CharSequence coins = "Coins gained: " + coinsEarned;
        font.draw(batch, coins, 500, 450);

        CharSequence allCoins = "Coins overall: " + collector.getCoins();
        font.draw(batch, allCoins, 500, 500);

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
