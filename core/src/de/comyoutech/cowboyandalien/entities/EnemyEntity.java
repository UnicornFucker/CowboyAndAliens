package de.comyoutech.cowboyandalien.entities;

/**
 * Represents an enemy.
 * 
 * @author BrookZ
 * 
 */
public class EnemyEntity extends AbstractEntity {

    /**
     * Movement speed of the enemy.
     */
    final float SPEED = 2f;

    /**
     * If the player is actually facing to the left side..
     */
    public boolean facingLeft = true;
    /**
     * If the Enemy is super ( =Endboss).
     */
    public boolean iAmSuper = false;

    /**
     * The lives the enemy has.
     */
    private int lives = 1;

    /**
     * The time since the last shot.
     */
    private float timeSinceLastShot;

    public EnemyEntity(float x, float y, boolean iAmSuper) {
        super(x, y, 4F);
        this.iAmSuper = iAmSuper;
        if (iAmSuper) {
            lives = 15;
        }
    }

    public EnemyEntity(float x, float y) {
        super(x, y);
    }

    public int getLives() {
        return lives;
    }

    public void wasShooten() {
        lives -= 1;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    /**
     * Change the direction of the enemy.
     */
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

    @Override
    protected void setSize() {
        SIZE = 0.5F;
    }

}
