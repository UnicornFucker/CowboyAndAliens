package de.comyoutech.cowboyandalien.entities;

/**
 * Represents a coin.
 * 
 * @author BrookZ
 * 
 */
public class CoinEntity extends AbstractEntity {

    /**
     * If the Coin is a Super coin.
     */
    public boolean iAmSuper;

    public CoinEntity(float x, float y) {
        super(x + 0.4F, y + 0.2F);
    }

    public CoinEntity(float x, float y, boolean iAmSuper) {
        super(x + 0.2F, y + 0.1F, 0.4F);
        this.iAmSuper = iAmSuper;
    }

    @Override
    protected void setSize() {
        SIZE = 0.2F;
    }

}
