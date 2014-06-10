package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class EnemyEntity extends Entity {

    final float SPEED = 0.0001f;
    final float JUMP_VELOCITY = 2f;

    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();

    public boolean facingLeft = false;

    private float timeSinceLastShot;

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

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

    public Vector2 getAcceleration() {
//        if (facingLeft) {
//            acceleration.x = SPEED;
//        }
//        else {
//            acceleration.x = -SPEED;
//        }
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
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

    public float getJumpVelocity() {
        return JUMP_VELOCITY;
    }

    public EnemyEntity(float x, float y) {
        super(x, y);
        if (facingLeft) {
            acceleration.x = -SPEED;
        }
        else {
            acceleration.x = SPEED;
        }
    }

    @Override
    protected void setSize() {
        SIZE = 0.4F;
    }

}
