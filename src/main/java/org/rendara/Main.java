package org.rendara;

import org.rendara.asset.Asset;
import org.rendara.asset.AssetServer;
import org.rendara.defaultplugin.DefaultPlugin;
import org.rendara.ecs.System;
import org.rendara.ecs.SystemEvent;
import org.rendara.event.*;
import org.rendara.graphic.*;
import org.rendara.math.Vec2f;
import org.rendara.math.Vec3;


class Spawn implements System {
    @Override
    public void system(World world) {
        AssetServer assetServer = world.getResource(AssetServer.class);
        Asset asset = assetServer.loadAsset("atlas.png");
        Texture texture = new Texture(asset.getImage());
        float[] vertices = {
                -0.5f, -0.5f, -0.5f,    1.0f, 0.0f, 0.0f,
                0.5f, -0.5f, -0.5f,    0.0f, 1.0f, 0.0f,
                0.5f,  0.5f, -0.5f,    0.0f, 0.0f, 1.0f,
                -0.5f,  0.5f, -0.5f,    1.0f, 1.0f, 0.0f,
                -0.5f, -0.5f,  0.5f,    1.0f, 0.0f, 1.0f,
                0.5f, -0.5f,  0.5f,    0.0f, 1.0f, 1.0f,
                0.5f,  0.5f,  0.5f,    1.0f, 1.0f, 1.0f,
                -0.5f,  0.5f,  0.5f,    0.0f, 0.0f, 0.0f,
        };

        int[] indices = {
                0, 1, 2, 2, 3, 0,
                // front face
                4, 5, 6, 6, 7, 4,
                // left face
                0, 4, 7, 7, 3, 0,
                // right face
                1, 5, 6, 6, 2, 1,
                // bottom face
                0, 1, 5, 5, 4, 0,
                // top face
                3, 2, 6, 6, 7, 3
        };
        Transform transform = new Transform(0, 0, 0);
        Mesh mesh = new Mesh(vertices, indices);
        mesh.addComponent(texture);
        mesh.addComponent(transform);
        world.spawn(mesh);
        world.spawn(new Camera(
                new Vec3(0.0f, 0.0f, 3.0f),
                new Vec3(0.0f, 1.0f, 0.0f),
                90.0f,
                0.0f
        ));

    }
}

//
//@OnEvent(EventKind.KEYBOARD)
//class Key implements System {
//    @Override
//    public void system(World world) {
//        KeyboardEvent event = world.getResource(KeyboardEvent.class);
//
//        Camera camera = world.getComponent(Camera.class);
//
//        if (event.kind == KeyboardKind.KEY_W) {
//            camera.processKeyboard(CameraMovement.FORWARD, 0.1f);
//        } else if (event.kind == KeyboardKind.KEY_S) {
//            camera.processKeyboard(CameraMovement.BACKWARD, 0.1f);
//        } else if (event.kind == KeyboardKind.KEY_A) {
//            camera.processKeyboard(CameraMovement.LEFT, 0.1f);
//        } else if (event.kind == KeyboardKind.KEY_D) {
//            camera.processKeyboard(CameraMovement.RIGHT, 0.1f);
//        }
//    }
//}

@OnCursor
class Cursor implements System {
    @Override
    public void system(World world) {
        // move the camera
        MouseEvent event = world.getResource(MouseEvent.class);
        Camera camera = world.getComponent(Camera.class);
        camera.processMouseMovement((float) event.x, (float) event.y, true);

    }
}


public class Main {
    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig();
        config.useLogger();
        config.useAssetServer();


        Context context = new Context(config);

        Application app = context.createApplication();

        DefaultPlugin plugin = new DefaultPlugin();

        plugin.withWindowTitle("Hello World");
        app.applyPlugin(plugin);

        app.addSystem(SystemEvent.SYSTEM_EVENT_STARTUP, new Spawn());
//        app.addSystem(SystemEvent.SYSTEM_EVENT_UPDATE, new Key());
        app.addSystem(SystemEvent.SYSTEM_EVENT_UPDATE, new Cursor());

        app.run();
    }
}