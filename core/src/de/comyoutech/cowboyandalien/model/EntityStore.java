package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import de.comyoutech.cowboyandalien.entities.AbstractEntity;
import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;

/**
 * Stores all entities and some general informations.
 * 
 * @author Leo
 * 
 */
public class EntityStore {

    /**
     * The player object.
     */
    public static PlayerEntity player;
    /**
     * The width of a level.
     */
    public static float levelWidth = 10F;
    /**
     * The height of a level.
     */
    public static float levelHeight = 7F;

    /**
     * The list that stores all entities.
     */
    public static List<AbstractEntity> entityList;
    /**
     * Rectangles with which the player can collide.
     */
    public static Array<Rectangle> collisionRects = new Array<Rectangle>();

    /**
     * Sets up the store.
     */
    public static void setUp() {
        entityList = new ArrayList<AbstractEntity>();
        player = EntityGenerator.generatePlayer();
        generateSomeContent();
    }

    /**
     * Generates content in the entityList.
     */
    private static void generateSomeContent() {
        EntityGenerator.generateLevelIn(entityList);
    }

    /**
     * Removes an entity from the list.
     * 
     * @param e
     *            the Entity to remvome.
     */
    public static void remove(AbstractEntity e) {
        entityList.remove(e);
    }

    /**
     * Returns the Block on the specified position.
     * 
     * @param x
     *            The x-position.
     * @param y
     *            The y-position.
     * @return
     */
    public static BlockEntity get(int x, int y) {
        for (AbstractEntity e : entityList) {
            if (e instanceof BlockEntity) {
                if ((e.getPosition().x == x) && (e.getPosition().y == y)) {
                    return (BlockEntity) e;
                }
            }
        }
        return null;
    }

}
