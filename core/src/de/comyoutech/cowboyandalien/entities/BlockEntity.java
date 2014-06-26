package de.comyoutech.cowboyandalien.entities;

/**
 * Represents a block.
 * 
 * @author BrookZ
 * 
 */
public class BlockEntity extends AbstractEntity {

    /**
     * If the block is an iceblock.
     */
    public boolean isIce = false;

    public BlockEntity(float x, float y) {
        super(x, y);
    }

    public BlockEntity(float x, float y, boolean isIce) {
        super(x, y);
        this.isIce = isIce;
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
