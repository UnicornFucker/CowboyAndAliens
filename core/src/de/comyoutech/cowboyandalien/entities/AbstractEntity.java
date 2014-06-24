package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractEntity {

    public static float SIZE;
    protected Rectangle bounds = new Rectangle();
    protected Vector2 position = new Vector2();
    float stateTime = 0;

    public float getStateTime() {
        return stateTime;
    }

    protected void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.setX(position.x);
        bounds.setY(position.y);
    }

    public AbstractEntity(float x, float y) {
        setSize();
        position.x = x;
        position.y = y;
        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = SIZE;
        bounds.height = SIZE;
    }

    public AbstractEntity(float x, float y, float size) {
        position.x = x;
        position.y = y;
        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = size;
        bounds.height = size;
    }

    protected abstract void setSize();

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        bounds.x = position.x;
        bounds.y = position.y;
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
