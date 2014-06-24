package de.comyoutech.cowboyandalien.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;

import de.comyoutech.cowboyandalien.items.AbstractItem;
import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.model.DataCollector;
import de.comyoutech.cowboyandalien.screens.DeadScreen;
import de.comyoutech.cowboyandalien.screens.GameScreen;
import de.comyoutech.cowboyandalien.screens.MenuScreen;

public class MyGdxGame extends Game {

    @Override
    public void create() {
        setMenuScreen();
        initializeCollector();
        Assets.load();
    }

    private void initializeCollector() {
        DataCollector collector = DataCollector.getInstance();

        int coins = 0;
        List<AbstractItem> itemList = new ArrayList<AbstractItem>();

        collector.addCoins(coins);
        collector.addItems(itemList);

    }

    public void setMenuScreen() {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    public void setGameScreen() {
        setScreen(new GameScreen(this));
    }

    public void setDeadScreen() {
        setScreen(new DeadScreen(this));
    }

}
