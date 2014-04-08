package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

public class ShotEntity extends Entity {

    public ShotEntity(int x, int y) {
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
