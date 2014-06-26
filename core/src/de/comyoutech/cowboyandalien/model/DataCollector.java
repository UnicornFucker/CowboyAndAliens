package de.comyoutech.cowboyandalien.model;

/**
 * Collects several datas.
 * 
 * @author BrookZ
 * 
 */
public class DataCollector {

    /**
     * Singleton-object.
     */
    private static DataCollector collector;

    /**
     * The coins the player has earned.
     */
    private int coins = 0;
    /**
     * The coins the player has collected in one lifecycle.
     */
    private int tempCoins = 0;

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
