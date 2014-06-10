package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;

public class EntityGenerator {

    public static void generateLevelIn(List<Entity> blockList) {
        // for (int i = 0; i < 10; i++) {
        // blockList.add(new BlockEntity(i, 0));
        // blockList.add(new BlockEntity(i, 6));
        // if (i > 2) {
        // blockList.add(new BlockEntity(i, 1));
        // }
        // blockList.add(new BlockEntity(9, 2));
        // blockList.add(new BlockEntity(9, 3));
        // blockList.add(new BlockEntity(9, 4));
        // blockList.add(new BlockEntity(9, 5));
        //
        // blockList.add(new BlockEntity(6, 3));
        // blockList.add(new BlockEntity(6, 4));
        // blockList.add(new BlockEntity(6, 5));
        // }
        generateTestLvl1(blockList);
    }

    private static void generateTestLvl2(List<Entity> blockList) {
        blockList.add(new EnemyEntity(5, 0));

        blockList.add(new BlockEntity(3, 0));

        blockList.add(new BlockEntity(7, 0));

    }

    private static void generateTestLvl1(List<Entity> blockList) {

        List<Integer> holes = new ArrayList<Integer>();
        Random random = new Random();

        int width = 100;

        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(3);
            for (int o = x; o < y; o++) {
                holes.add(o);
            }
            holes.add(x);
        }
        for (int i = 0; i < width; i++) {
            if (!holes.contains(i)) {
                blockList.add(new BlockEntity(i, 0));
            } else {
                // TODO HoleEntity
            }
        }

        blockList.add(new BlockEntity(11, 1));
        blockList.add(new BlockEntity(15, 1));
        blockList.add(new BlockEntity(16, 1));
        blockList.add(new BlockEntity(16, 2));
        blockList.add(new BlockEntity(17, 2));
        blockList.add(new BlockEntity(18, 2));
        blockList.add(new BlockEntity(19, 2));
        blockList.add(new BlockEntity(20, 2));
        blockList.add(new BlockEntity(20, 3));
        blockList.add(new BlockEntity(21, 4));
        blockList.add(new BlockEntity(21, 2));
    }

    public static PlayerEntity generatePlayer() {
        return new PlayerEntity(7, 4);
    }
}
