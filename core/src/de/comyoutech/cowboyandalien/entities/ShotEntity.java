package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class ShotEntity extends AbstractEntity {

    private final float SPEED = 5f;

    public boolean left;

    public boolean killsPlayer;

    private boolean facingLeft;

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public float getSPEED() {
        return SPEED;
    }

    public ShotEntity(float x, float y, boolean killsPlayer) {
        super(x, y);
        this.killsPlayer = killsPlayer;
    }

    public ShotEntity(float x, float y, float size) {
        super(x, y);
        SIZE = size;
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    protected void setSize() {
        SIZE = 0.1F;
    }

}
