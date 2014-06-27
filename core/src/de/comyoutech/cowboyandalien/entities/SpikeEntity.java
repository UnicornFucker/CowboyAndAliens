package de.comyoutech.cowboyandalien.entities;

/**
 * Represents a Spike Entity.
 * 
 * @author Georg
 * 
 */
public class SpikeEntity extends AbstractEntity {

    public SpikeEntity(float x, float y) {
        super(x, y);
        bounds.width = 1F;
        bounds.height = 0.8F;
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
