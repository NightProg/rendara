package org.rendara.graphic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;
import org.rendara.Context;
import org.rendara.ContextHolder;
import org.rendara.OSInfo;
import org.rendara.World;
import org.rendara.ecs.Entity;
import org.rendara.exception.GLFWError;

public class WindowEntity extends Entity {
    Window window;
    long windowId;

    public WindowEntity(int width, int height, String title) {
        window = new Window(width, height, title);
        addComponent(window);
    }

    @Override
    public void render(World world) {
        Context context = ContextHolder.getContext();
        if (context.isOpenGLInitialized()) {
            throw new RuntimeException("OpenGL is already initialized");
        }


        // Mac OS X specific hints
        if (OSInfo.isMac()) {
            GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
            GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
            GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
        } else {
            GLFW.glfwDefaultWindowHints();
        }

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, context
                .getConfig()
                .getOpenGLVersion()
                .getMajor()
        );
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, context
                .getConfig()
                .getOpenGLVersion()
                .getMinor()
        );

        GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE);

        windowId = GLFW.glfwCreateWindow(window.getWidth(), window.getHeight(), window.getTitle(), MemoryUtil.NULL, MemoryUtil.NULL);

        if (windowId == 0) {
            throw new GLFWError("Failed to create window");
        }

        GLFW.glfwMakeContextCurrent(windowId);
        context.glInit();

        window.setWindowId(windowId);
        context.setWindow(window);

        GL33.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }
}
