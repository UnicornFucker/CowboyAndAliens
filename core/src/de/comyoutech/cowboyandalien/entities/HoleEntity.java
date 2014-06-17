package de.comyoutech.cowboyandalien.entities;

public class HoleEntity extends Entity {

    public HoleEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
