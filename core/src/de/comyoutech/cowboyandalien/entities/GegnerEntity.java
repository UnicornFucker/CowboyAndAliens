package de.comyoutech.cowboyandalien.entities;

import com.badlogic.gdx.math.Vector2;

import de.comyoutech.cowboyandalien.model.EntityGenerator;

public class GegnerEntity extends Entity {

    EntityGenerator entity;

    public GegnerEntity(int x, int y) {
        super(x, y);

    }

    @Override
    public Vector2 getPosition() {

        super.getPosition().x -= 0.01;

        return super.getPosition();

    }

    @Override
    protected void setSize() {
        SIZE = 0.5F;
    }

}
