package de.comyoutech.cowboyandalien.entities;


public class BlockEntity extends Entity {

    public BlockEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
