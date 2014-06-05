package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;

public class EntityStore {

    public static PlayerEntity player;
    public static float levelWidth = 10F;
    public static float levelHeight = 7F;
    public static List<Entity> entityList;
    public static Array<Rectangle> collisionRects = new Array<Rectangle>();

    public static void setUp() {
        entityList = new ArrayList<Entity>();
        player = EntityGenerator.generatePlayer();
        generateSomeContent();
    }

    private static void generateSomeContent() {
        EntityGenerator.generateLevelIn(entityList);
    }

    public static void remove(Entity e) {
        entityList.remove(e);
    }

    public static List<BlockEntity> getDrawableBlocks(int width, int height) {
        int x = (int) player.getPosition().x - width;
        int y = (int) player.getPosition().y - height;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        int x2 = x + (2 * width);
        int y2 = y + (2 * height);
        if (x2 > levelHeight) {
            x2 = (int) levelWidth - 1;
        }
        if (y2 > levelHeight) {
            y2 = (int) levelHeight - 1;
        }

        List<BlockEntity> blocks = new ArrayList<BlockEntity>();
        BlockEntity block;
        for (int col = x; col <= x2; col++) {
            for (int row = y; row <= y2; row++) {
                block = get(col, row);
                if (block != null) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static BlockEntity get(int x, int y) {
        for (Entity e : entityList) {
            if (e instanceof BlockEntity) {
                if ((e.getPosition().x == x) && (e.getPosition().y == y)) {
                    return (BlockEntity) e;
                }
            }
        }
        return null;
    }

}
