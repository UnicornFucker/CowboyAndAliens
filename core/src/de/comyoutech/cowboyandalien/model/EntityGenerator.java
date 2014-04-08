package de.comyoutech.cowboyandalien.model;

import java.util.List;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;

public class EntityGenerator {

    public static void generateLevelIn(List<Entity> blockList) {
        for (int i = 0; i < 10; i++) {
            blockList.add(new BlockEntity(i, 0));
            blockList.add(new BlockEntity(i, 6));
            if (i > 2) {
                blockList.add(new BlockEntity(i, 1));
            }
            blockList.add(new BlockEntity(9, 2));
            blockList.add(new BlockEntity(9, 3));
            blockList.add(new BlockEntity(9, 4));
            blockList.add(new BlockEntity(9, 5));

            blockList.add(new BlockEntity(6, 3));
            blockList.add(new BlockEntity(6, 4));
            blockList.add(new BlockEntity(6, 5));
        }

    }

    public static PlayerEntity generatePlayer() {
        return new PlayerEntity(7, 5);
    }

}
