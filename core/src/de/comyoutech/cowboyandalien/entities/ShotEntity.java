package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class ShotEntity extends Entity {

    private final float SPEED = 10f;

    public boolean left;

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

    public ShotEntity(float x, float y) {
        super(x, y);
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
