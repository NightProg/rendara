package org.rendara.graphic;

import org.rendara.ContextHolder;
import org.rendara.World;
import org.rendara.ecs.Entity;
import org.rendara.ecs.ShouldBeUpdated;

@ShouldBeUpdated
public class CameraEntity extends Entity {
    public CameraEntity(Camera camera) {
        super();
        addComponent(camera);
    }

    @Override
    public void render(World world) {
        ContextHolder.getContext().needAProgram();
        ContextHolder.getContext().getProgram().use();
        Camera camera = getComponent(Camera.class);
        ContextHolder.getContext().getProgram().setUniform("view", getComponent(Camera.class).getViewMatrix());
    }
}
