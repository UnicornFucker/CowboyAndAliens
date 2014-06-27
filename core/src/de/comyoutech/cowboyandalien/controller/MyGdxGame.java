package de.comyoutech.cowboyandalien.controller;

import com.badlogic.gdx.Game;

import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.model.DataCollector;
import de.comyoutech.cowboyandalien.model.EntityStore;
import de.comyoutech.cowboyandalien.screens.DeadScreen;
import de.comyoutech.cowboyandalien.screens.GameScreen;
import de.comyoutech.cowboyandalien.screens.LevelScreen;
import de.comyoutech.cowboyandalien.screens.MenuScreen;

public class MyGdxGame extends Game {

    /**
     * Enum for game states.
     * 
     * @author BrookZ
     * 
     */
    public enum GameState {
        PAUSE, RUN,
    }

    /**
     * State of the game.
     */
    public GameState gameState = GameState.RUN;

    @Override
    public void create() {
        setMenuScreen();
        initializeCollector();
        Assets.load();
    }

    /**
     * Initializes Datas.
     */
    private void initializeCollector() {
        DataCollector collector = DataCollector.getInstance();

        int coins = 0;

        collector.addCoins(coins);

    }

    /**
     * Sets the MenuScreen as the actual Screen.
     */
    public void setMenuScreen() {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    /**
     * Sets the GameScreen as the actual Screen.
     */
    public void setGameScreen() {
        EntityStore.setUp();
        setScreen(new GameScreen(this));
    }

    /**
     * Sets the DeadScreen as the actual Screen.
     */
    public void setDeadScreen() {
        setScreen(new DeadScreen(this));
    }

    /**
     * Sets the LevelSolvedScreen as the actual Screen.
     */
    public void setLevelSolvedScreen() {
        Assets.soundBackgroundBoss.stop();
        setScreen(new LevelScreen(this));
    }

}
