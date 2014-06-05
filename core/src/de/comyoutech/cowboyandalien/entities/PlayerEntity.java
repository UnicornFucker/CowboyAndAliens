package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends Entity {
    public enum State {
        IDLE, WALKING, JUMPING, DYING
    }

    static final float SPEED = 4f;
    static final float JUMP_VELOCITY = 2f; // Sprunghöhe
    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();
    boolean facingLeft = true;

    private Animation animation;
    State state = State.IDLE;
    boolean longJump = false;

    public PlayerEntity(float x, float y) {
        super(x, y);
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isLongJump() {
        return longJump;
    }

    public void setLongJump(boolean longJump) {
        this.longJump = longJump;
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
