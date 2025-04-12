package org.rendara.ecs;

import org.rendara.World;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    static int id = 0;

    List<Component> components;

    public Entity() {
        id++;
        components = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addComponent(Component component) {
        component.linkWithEntity(this);
    }

    public boolean hasComponent(Class<?> component) {
        for (Component c : components) {
            if (c.getClass() == component) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> component) {
        for (Component c : components) {
            if (c.getClass() == component) {
                return (T) c;
            }
        }
        return null;
    }


    public void render(World world) {

    }

    public void startup(World world) {
    }

}
