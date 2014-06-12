package de.comyoutech.cowboyandalien.entities;

public class CoinEntity extends Entity {

    public CoinEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 0.5F;
    }

}
