package de.comyoutech.cowboyandalien.model;

import java.util.List;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
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
        generateTestLvl2(blockList);
    }

    private static void generateTestLvl2(List<Entity> blockList) {
        blockList.add(new EnemyEntity(5, 0));

        blockList.add(new BlockEntity(2, 0));

        blockList.add(new BlockEntity(9, 0));

        blockList.add(new MovableBlockEntity(5, 2, false));
        blockList.add(new MovableBlockEntity(0, 2, false));
        blockList.add(new MovableBlockEntity(4, 1, true));
        blockList.add(new MovableBlockEntity(5, 2, true));
        blockList.add(new MovableBlockEntity(3, 2, false));
        blockList.add(new MovableBlockEntity(5, 2, true));
        blockList.add(new MovableBlockEntity(8, 6, false));

    }

    private static void generateTestLvl1(List<Entity> blockList) {
        blockList.add(new BlockEntity(7, 0));
        blockList.add(new BlockEntity(10, 0));
        blockList.add(new BlockEntity(11, 0));
        blockList.add(new BlockEntity(11, 1));
        blockList.add(new BlockEntity(14, 0));
        blockList.add(new BlockEntity(15, 0));
        blockList.add(new BlockEntity(15, 1));
        blockList.add(new BlockEntity(16, 0));
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
