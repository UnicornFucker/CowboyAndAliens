package de.comyoutech.cowboyandalien.model;

import java.util.ArrayList;
import java.util.List;

import de.comyoutech.cowboyandalien.entities.Entity;

public class EntityStore {

    private List<Entity> entityList;

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public EntityStore() {
        entityList = new ArrayList<Entity>();
        generateSomeContent();
    }

    private void generateSomeContent() {
        entityList.add(EntityGenerator.generatePlayer());
        EntityGenerator.generateLevelIn(entityList);
        entityList.add(EntityGenerator.generateGegner());
        EntityGenerator.generateLevelIn(entityList);
    }

    public void remove(Entity e) {
        entityList.remove(e);
    }

}
