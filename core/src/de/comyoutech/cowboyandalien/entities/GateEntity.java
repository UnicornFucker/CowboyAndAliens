package de.comyoutech.cowboyandalien.entities;

/**
 * Represents the gate to the next level.
 * 
 * @author Felix
 * 
 */
public class GateEntity extends AbstractEntity {

    public GateEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

}
