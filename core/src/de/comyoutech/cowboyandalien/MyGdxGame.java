package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Game;

/** Import **/

public class MyGdxGame extends Game {

    @Override
    public void create() {
    	Assets.load();
        setScreen(new GameScreen());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();
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

}
