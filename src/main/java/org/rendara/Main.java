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

        Vec2f[] uvs = GraphicUtil.getUVs(asset.getImage(), 0, 0, 256, 256);
        float[] vertices = {
                -0.5f, -0.5f, -0.5f, uvs[0].x, uvs[0].y,
                0.5f, -0.5f, -0.5f, uvs[1].x, uvs[1].y,
                0.5f, 0.5f, -0.5f, uvs[2].x, uvs[2].y,
                0.5f, 0.5f, -0.5f, uvs[3].x, uvs[3].y,
                -0.5f, 0.5f, -0.5f, uvs[4].x, uvs[4].y,
                -0.5f, -0.5f, -0.5f, uvs[5].x, uvs[5].y,

                -0.5f, -0.5f, 0.5f, uvs[0].x, uvs[0].y,
                0.5f, -0.5f, 0.5f, uvs[1].x, uvs[1].y,
                0.5f, 0.5f, 0.5f, uvs[2].x, uvs[2].y,
                0.5f, 0.5f, 0.5f, uvs[3].x, uvs[3].y,
                -0.5f, 0.5f, 0.5f, uvs[4].x, uvs[4].y,
                -0.5f, -0.5f, 0.5f, uvs[5].x, uvs[5].y,

                -0.5f, 0.5f, 0.5f, uvs[0].x, uvs[0].y,
                -0.5f, 0.5f, -0.5f, uvs[1].x, uvs[1].y,
                -0.5f, -0.5f, -0.5f, uvs[2].x, uvs[2].y,
                -0.5f, -0.5f, -0.5f, uvs[3].x, uvs[3].y,
                -0.5f, -0.5f, 0.5f, uvs[4].x, uvs[4].y,
                -0.5f, 0.5f, 0.5f, uvs[5].x, uvs[5].y,

                0.5f, 0.5f, 0.5f, uvs[0].x, uvs[0].y,
                0.5f, 0.5f, -0.5f, uvs[1].x, uvs[1].y,
                0.5f, -0.5f, -0.5f, uvs[2].x, uvs[2].y,
                0.5f, -0.5f, -0.5f, uvs[3].x, uvs[3].y,
                0.5f, -0.5f, 0.5f, uvs[4].x, uvs[4].y,
                0.5f, 0.5f, 0.5f, uvs[5].x, uvs[5].y,

                -0.5f, -0.5f, -0.5f, uvs[0].x, uvs[0].y,
                0.5f, -0.5f, -0.5f, uvs[1].x, uvs[1].y,
                0.5f, -0.5f, 0.5f, uvs[2].x, uvs[2].y,
                0.5f, -0.5f, 0.5f, uvs[3].x, uvs[3].y,
                -0.5f, -0.5f, 0.5f, uvs[4].x, uvs[4].y,
                -0.5f, -0.5f, -0.5f, uvs[5].x, uvs[5].y,

                -0.5f, 0.5f, -0.5f, uvs[0].x, uvs[0].y,
                0.5f, 0.5f, -0.5f, uvs[1].x, uvs[1].y,
                0.5f, 0.5f, 0.5f, uvs[2].x, uvs[2].y,
                0.5f, 0.5f, 0.5f, uvs[3].x, uvs[3].y,
                -0.5f, 0.5f, 0.5f, uvs[4].x, uvs[4].y,
                -0.5f, 0.5f, -0.5f, uvs[5].x, uvs[5].y
        };

        int[] indices = {
                0, 1, 2, 2, 3, 4,
                5, 6, 7, 7, 8, 9,
                10, 11, 12, 12, 13, 14,
                15, 16, 17, 17, 18, 19,
                20, 21, 22, 22, 23, 24,
                25, 26, 27, 27, 28, 29,
                30, 31, 32, 32, 33, 34,
                35
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

//@OnCursor
//class Cursor implements System {
//    @Override
//    public void system(World world) {
//        // move the camera
//        MouseEvent event = world.getResource(MouseEvent.class);
//        Camera camera = world.getComponent(Camera.class);
//        camera.processMouseMovement((float) event.x, (float) event.y, true);
//
//    }
//}


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
//        app.addSystem(SystemEvent.SYSTEM_EVENT_UPDATE, new Cursor());

        app.run();
    }
}