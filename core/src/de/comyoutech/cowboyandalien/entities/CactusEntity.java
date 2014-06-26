package de.comyoutech.cowboyandalien.entities;

/**
 * Represents a Cactus.
 * 
 * @author BrookZ
 */
public class CactusEntity extends AbstractEntity {

    public CactusEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
