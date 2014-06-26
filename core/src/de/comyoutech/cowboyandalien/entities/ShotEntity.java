package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a shot.
 * 
 * @author BrookZ
 * 
 */
public class ShotEntity extends AbstractEntity {

    /**
     * The speed of the shot.
     */
    private final float SPEED = 5f;

    /**
     * If the shot kills the player.
     */
    public boolean killsPlayer;

    /**
     * If the shot is facing to the left side.
     */
    private boolean facingLeft;

    public ShotEntity(float x, float y, boolean killsPlayer) {
        super(x, y);
        this.killsPlayer = killsPlayer;
    }

    public ShotEntity(float x, float y, float size) {
        super(x, y);
        SIZE = size;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public float getSPEED() {
        return SPEED;
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    protected void setSize() {
        SIZE = 0.2F;
    }

}
