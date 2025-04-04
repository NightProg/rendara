package org.rendara;

import org.rendara.ecs.*;
import org.rendara.ecs.System;

import java.util.ArrayList;
import java.util.List;

public class World {

    List<Resource> resources;
    List<Entity> entities;
    Application application;

    public World(Application app) {
        this.resources = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.application = app;
    }

    @SuppressWarnings("unchecked")
    public <T extends Resource> T getResource(Class<T> resource) {
        for (Resource r : resources) {
            if (r.getClass() == resource) {
                return (T) r;
            }
        }

        return null;
    }

    List<Entity> getEntitiesWithComponent(Class<?> component) {
        return null;
    }

    public <T extends Component> T getComponent(Class<T> component) {
        for (Entity entity : entities) {
            java.lang.System.out.println(entity);
            if (entity.hasComponent(component)) {
                return entity.getComponent(component);
            }
        }
        return null;
    }


    public void spawn(Entity entity) {

        if (entity.getClass().getAnnotation(ShouldBeUpdated.class) != null) {
            application.addSystem(SystemEvent.SYSTEM_EVENT_UPDATE, entity::render);
        } else {
            entity.render(this);
        }

        entities.add(entity);
    }

    public <T extends AsEntity> void  spawn(T entity) {
        spawn(entity.asEntity());
    }


}
