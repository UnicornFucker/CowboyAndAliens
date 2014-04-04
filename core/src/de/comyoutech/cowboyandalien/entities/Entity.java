package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 position = new Vector2();

    static float SIZE; // größe
    private Rectangle bounds = new Rectangle();

    public Entity(int x, int y) {
        SIZE = 1F;
        position.x = x;
        position.y = y;
        bounds.width = SIZE;
        bounds.height = SIZE;
    }

    protected abstract void setSize();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
