package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class which represents any entity that is displayed in the game.
 * 
 * @author BrookZ
 * 
 */
public abstract class AbstractEntity {

    /**
     * The size of the entity.
     */
    public static float SIZE;
    /**
     * The bounds of the entitiy.
     */
    protected Rectangle bounds = new Rectangle();
    /**
     * The position of the entity.
     */
    protected Vector2 position = new Vector2();
    /**
     * The time the entity lasts in a state.
     */
    float stateTime = 0;

    /**
     * Constructor with x, y. (Position)
     * 
     * @param x X-Position;
     * @param y Y-Position.
     */
    public AbstractEntity(float x, float y) {
        setSize();
        position.x = x;
        position.y = y;
        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = SIZE;
        bounds.height = SIZE;
    }

    /**
     * Constructor with custom SIZE.
     * 
     * @param size Custom size.
     */
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

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.setX(position.x);
        bounds.setY(position.y);
    }
}
