package de.comyoutech.cowboyandalien.entities;

public class EnemyEntity extends Entity {

    final float SPEED = 2f;

    public boolean facingLeft = true;

    private float timeSinceLastShot;

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    // TODO Verschiedene Bilder
    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    public void switchDirection() {
        if (facingLeft) {
            facingLeft = false;
        }
        else {
            facingLeft = true;
        }
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public float getSpeed() {
        return SPEED;
    }

    public EnemyEntity(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 0.5F;
    }

}
