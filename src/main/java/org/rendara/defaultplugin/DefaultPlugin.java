package org.rendara.defaultplugin;

import org.rendara.Application;
import org.rendara.ContextHolder;
import org.rendara.asset.AssetServer;
import org.rendara.ecs.Plugin;
import org.rendara.ecs.SystemEvent;

public class DefaultPlugin implements Plugin {

    private String windowTitle = "Rendara";
    private int windowWidth = 800;
    private int windowHeight = 600;


    public void withWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public void withWindowSize(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
    }

    public void withWindowSize(int size) {
        this.windowWidth = size;
        this.windowHeight = size;
    }

    @Override
    public void build(Application app) {
        app.insertResource(
                new AssetServer(ContextHolder
                        .getContext()
                        .getConfig()
                        .getCustomAssetPath()
                )
        );

        app.addSystem(
                SystemEvent.SYSTEM_EVENT_STARTUP, new SimpleWindows(windowWidth, windowHeight, windowTitle)
        );
    }


}
