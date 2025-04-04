package org.rendara.ecs;

import org.rendara.World;

public class Component {
    static long id = 0;
    Entity parentEntity;


    public Component() {
        id++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void linkWithEntity(Entity entity) {
        entity.components.add(this);
        parentEntity = entity;
    }
    public Entity getParentEntity() {
        return parentEntity;
    }
}
