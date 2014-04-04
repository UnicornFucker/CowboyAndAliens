package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends Entity {

    public enum State {
        IDLE, WALKING, JUMPING, DYING
    }

    boolean facingLeft = true;

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public State state = State.IDLE;

    static final float SPEED = 4f; // movementSpeed
    static final float JUMP_VELOCITY = 1f; // Sprunghöhe
    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setState(State newState) {
        state = newState;
    }

    public State getState() {
        return state;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public PlayerEntity(int x, int y) {
        super(x, y);
    }

    @Override
    protected void setSize() {
        SIZE = 0.5F;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }

}
