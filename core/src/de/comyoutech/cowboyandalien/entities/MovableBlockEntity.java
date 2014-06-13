package de.comyoutech.cowboyandalien.entities;

public class MovableBlockEntity extends Entity {

    public final float SPEED = 3F;
    public final float RANGE = 1f;

    private final float maxRange1;
    private final float maxRange2;

    public boolean moveForward = true;

    public boolean vertical;

    public MovableBlockEntity(float x, float y, boolean vertical) {
        super(x, y);
        this.vertical = vertical;
        if (vertical) {
            maxRange1 = y - (RANGE / 2);
            maxRange2 = y + (RANGE + 2);
        }
        else {
            maxRange1 = x - (RANGE / 2);
            maxRange2 = x + (RANGE + 2);
        }
    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

    public void checkRange() {

        float x = getPosition().x;
        float y = getPosition().y;

        if (vertical) {
            if (moveForward) {
                if (y > maxRange2) {
                    moveForward = false;
                }
            }
            else {
                if (y < maxRange1) {
                    moveForward = true;
                }
            }
        }
        else {
            if (moveForward) {
                if (x > maxRange2) {
                    moveForward = false;
                }
            }
            else {
                if (x < maxRange1) {
                    moveForward = true;
                }
            }
        }

    }

}
