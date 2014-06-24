package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;

import de.comyoutech.cowboyandalien.items.AbstractItem;

public class DataCollector {

    private static DataCollector collector;

    private List<AbstractItem> items;
    private int coins = 0;
    private int tempCoins = 0;

    private DataCollector() {
        items = new ArrayList<AbstractItem>();
    }

    public List<AbstractItem> getItems() {
        return items;
    }

    public void addItems(List<AbstractItem> itemList) {
        items.addAll(itemList);
    }

    public void addItem(AbstractItem item) {
        items.add(item);
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void transferCoins() {
        coins += tempCoins;
        tempCoins = 0;
    }

    public int getTempCoins() {
        return tempCoins;
    }

    public void addTempCoins(int tempCoins) {
        this.tempCoins += tempCoins;
    }

    public static DataCollector getInstance() {
        if (collector == null) {
            collector = new DataCollector();
        }
        return collector;
    }

}
