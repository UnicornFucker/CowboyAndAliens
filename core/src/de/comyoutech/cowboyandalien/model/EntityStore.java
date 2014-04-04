package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;

public class EntityStore {
    private List<BlockEntity> blockList;
    private PlayerEntity player;

    public EntityStore() {
        blockList = new ArrayList<BlockEntity>();
        generateSomeContent();
    }

    private void generateSomeContent() {
        setPlayer(EntityGenerator.generatePlayer());
        EntityGenerator.generateLevelIn(blockList);
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    private void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public List<BlockEntity> getBlockList() {
        return blockList;
    }

}
