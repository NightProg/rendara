package org.rendara;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import org.rendara.ecs.Plugin;
import org.rendara.ecs.Resource;
import org.rendara.ecs.SystemEvent;
import org.rendara.ecs.System;
import org.rendara.event.*;
import org.rendara.math.Mat4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Application {
    Hashmap<SystemEvent, List<System>> systems;
    AppLogger logger = new AppLogger(Application.class);
    World world;

    List<Resource> resources;

    Application() {
        this.resources = new ArrayList<>();
        this.world = new World(this);
        this.systems = new HashMap<>();
    }

    public Context getContext() {
        return ContextHolder.getContext();
    }


    public void insertResource(Resource resource) {
        resources.add(resource);
        world.resources.add(resource);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
        world.resources.remove(resource);
    }


    public void applyPlugin(Plugin plugin) {
        plugin.build(this);
    }

    public void addSystem(SystemEvent event, System system) {
        if (!systems.containsKey(event)) {
            ArrayList<System> systemList = new ArrayList<>();
            systemList.add(system);
            systems.put(event, systemList);
            return;
        }
        systems.get(event).add(system);
    }



    public void run() {
        insertResource(
                new Events()
        );
        List<System> startupSystems = systems.get(SystemEvent.SYSTEM_EVENT_STARTUP);
        for (System system : startupSystems) {

            system.system(world);

            logger.info("System started: {}", system.getClass().getName());
            int error = GL33.glGetError();
            if (error != GL33.GL_NO_ERROR) {
                java.lang.System.out.println("OpenGL error: " + error);
            }
        }


        GLFW.glfwSetKeyCallback(ContextHolder.getContext().getWindow().getWindowId(), (window, key, scancode, action, mods) -> {
            Events events = world.getResource(Events.class);
            Event event = new KeyboardEvent(
                    KeyboardKind.fromGLFW(key),
                    KeyboardModifier.fromGLFW(mods),
                    KeyboardAction.fromGLFW(action)
            );
            events.add(event);




        });

        GLFW.glfwSetCursorPosCallback(ContextHolder.getContext().getWindow().getWindowId(), (window, xpos, ypos) -> {
            Events events = world.getResource(Events.class);
            Event event = new MouseEvent(MouseKind.MOVE, xpos, ypos);
            events.add(event);
        });

        if (getContext().getProgram() != null) {
            getContext().getProgram().setUniform("projection", Mat4.perspective(45.0f, 800.0f / 600.0f, 0.1f, 100.0f));
        }

        while (!getContext().isCloseRequested()) {

            if (GLFW.glfwWindowShouldClose(ContextHolder.getContext().getWindow().getWindowId())) {
                ContextHolder.getContext().requestClose();
            }
            GL33.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);

            if (getContext().getProgram() != null) {
                getContext().getProgram().use();
            }
            List<System> updateSystems = systems.get(SystemEvent.SYSTEM_EVENT_UPDATE);
            if (updateSystems == null) {
                updateSystems = new ArrayList<>();
            }
            for (System system : updateSystems) {
                if (system.getClass().getAnnotation(OnEvent.class) != null) {
                    OnEvent onEvent = system.getClass().getAnnotation(OnEvent.class);
                    if (onEvent.value() == EventKind.KEYBOARD) {
                        Events events = world.getResource(Events.class);
                        KeyboardEvent event = (KeyboardEvent) events.get(EventKind.KEYBOARD);
                        insertResource(event);
                        if (event != null) {
                            system.system(world);
                        }
                        removeResource(event);
                        continue;
                    }
                } else if (system.getClass().getAnnotation(OnKeyboardPress.class) != null) {
                    OnKeyboardPress onKeyboardPress = system.getClass().getAnnotation(OnKeyboardPress.class);
                    Events events = world.getResource(Events.class);
                    KeyboardEvent event = (KeyboardEvent) events.get(EventKind.KEYBOARD);
                    if (event != null && event.kind == onKeyboardPress.value() && event.action == KeyboardAction.PRESS) {
                        insertResource(event);
                        system.system(world);
                        removeResource(event);
                    }
                    continue;
                } else if (system.getClass().getAnnotation(OnCursor.class) != null) {
                    Events events = world.getResource(Events.class);
                    MouseEvent event = (MouseEvent) events.get(EventKind.MOUSE);
                    if (event != null) {
                        insertResource(event);
                        system.system(world);
                        removeResource(event);
                    }
                    continue;
                }
                system.system(world);
                int error = GL33.glGetError();
                if (error != GL33.GL_NO_ERROR) {
                    java.lang.System.out.println("OpenGL error: " + error);
                }
            }

            GLFW.glfwSwapBuffers(ContextHolder.getContext().getWindow().getWindowId());

            GLFW.glfwPollEvents();
        }
    }
}
