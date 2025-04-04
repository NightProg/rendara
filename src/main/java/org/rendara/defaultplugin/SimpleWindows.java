package org.rendara.defaultplugin;

import org.rendara.World;
import org.rendara.ecs.Entity;
import org.rendara.ecs.System;
import org.rendara.graphic.Window;
import org.rendara.graphic.WindowEntity;

public class SimpleWindows implements System {
    WindowEntity windowEntity;

    public SimpleWindows(int width, int height, String title) {
        windowEntity = new WindowEntity(width, height, title);
    }

    @Override
    public void system(World world) {
        world.spawn(windowEntity);
    }
}
