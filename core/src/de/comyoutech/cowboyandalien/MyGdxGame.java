package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    public GameScreen gameScreen;
    public DeadScreen deadScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        deadScreen = new DeadScreen(this);
        setScreen(gameScreen);
    }

}
