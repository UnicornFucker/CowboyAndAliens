package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class ShotEntity extends Entity {

    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();

    public boolean left;

    public Vector2 getAcceleration() {
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

    public ShotEntity(float x, float y) {
        super(x, y);
    }

    @Override
    public Vector2 getPosition() {
        super.getPosition().x += 0.2;
        return super.getPosition();
    }

    @Override
    protected void setSize() {
        SIZE = 0.1F;
    }

}
