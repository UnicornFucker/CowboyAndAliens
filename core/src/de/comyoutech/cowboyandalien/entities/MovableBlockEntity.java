package de.comyoutech.cowboyandalien.entities;

import java.util.Random;

/**
 * Represents a Movable Entity.
 * 
 * @author Leo
 * 
 */
public class MovableBlockEntity extends AbstractEntity {

    /**
     * The speed the entity finally has.
     */
    public final float SPEEDFINAL;

    /**
     * The approximately speed of the entity.
     */
    public final float SPEED = 2F;

    /**
     * The Range in which the entity moves.
     */
    public final float RANGE;

    /**
     * The
     */
    private final float maxRange1;
    private final float maxRange2;

    /**
     * If the Entity moves forward or backwards.
     */
    public boolean moveForward = true;

    /**
     * If the Entity moves vertical.
     */
    public boolean vertical;

    public MovableBlockEntity(float x, float y, boolean vertical) {
        super(x, y);
        this.vertical = vertical;

        Random random = new Random();

        SPEEDFINAL = SPEED + random.nextFloat();

        float range = random.nextInt(4);
        RANGE = range;

        if (vertical) {
            maxRange1 = y - (RANGE / 2);
            maxRange2 = y + (RANGE + 2);
        }
        else {
            maxRange1 = x - (RANGE / 2);
            maxRange2 = x + (RANGE + 2);
        }
    }

    /**
     * 
     * @param x
     * @param y
     * @param range
     * @param vertical
     */
    public MovableBlockEntity(float x, float y, float range, boolean vertical) {
        super(x, y);
        this.vertical = vertical;
        RANGE = range;
        Random random = new Random();

        SPEEDFINAL = SPEED + (random.nextFloat() * 3);

        if (vertical) {
            maxRange1 = y - (RANGE / 2);
            maxRange2 = y + (RANGE / 2);
        }
        else {
            maxRange1 = x - (RANGE / 2);
            maxRange2 = x + (RANGE / 2);
        }

    }

    @Override
    protected void setSize() {
        SIZE = 1F;
    }

    /**
     * Checks if the Entity is moving out of range.
     */
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
