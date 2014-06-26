package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents the player.
 * 
 * @author BrookZ
 * 
 */
public class PlayerEntity extends AbstractEntity {

    /**
     * Enum for several player states.
     * 
     * @author BrookZ
     * 
     */
    public enum State {
        IDLE, WALKING, JUMPING, DYING
    }

    /**
     * The speed of the player.
     */
    static final float SPEED = 4f;
    /**
     * The jump velocity.
     */
    static final float JUMP_VELOCITY = 2f;
    /**
     * The acceleration of the player.
     */
    private Vector2 acceleration = new Vector2();
    /**
     * The velocity of the player.
     */
    private Vector2 velocity = new Vector2();
    /**
     * If the player is facing left.
     */
    boolean facingLeft = true;

    /**
     * The state of the player.
     */
    State state = State.IDLE;

    public PlayerEntity(float x, float y) {
        super(x, y);
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

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

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    protected void setSize() {
        SIZE = 0.3F;
    }

    public void update(float delta) {
        stateTime += delta;
    }

}
