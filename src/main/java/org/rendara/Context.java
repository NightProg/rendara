package org.rendara;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.rendara.exception.InitializationFailed;
import org.rendara.graphic.Window;
import org.rendara.shader.Program;
import org.rendara.shader.Shader;
import org.rendara.shader.SimpleShader;
import org.slf4j.Logger;

public class Context {

    ApplicationConfig config = new ApplicationConfig();
    Logger logger = org.slf4j.LoggerFactory.getLogger(Context.class);

    boolean isOpenGLInitialized = false;

    boolean isCloseRequested = false;

    Window window = null;

    Program program;

    public Context(ApplicationConfig config) {
        this.config = config;
        GLFWErrorCallback.createPrint(System.err).set();

        boolean res = GLFW.glfwInit();
        if (!res) {
            throw new InitializationFailed("GLFW");
        }

        if (config.logger) {
            logger.info("GLFW initialized");
        }


        ContextHolder.setContext(this);
    }

    public Context() {
        GLFWErrorCallback.createPrint(System.err).set();

        boolean res = GLFW.glfwInit();
        if (!res) {
            throw new InitializationFailed("GLFW");
        }

        if (config.logger) {
            logger.info("GLFW initialized");
        }


        ContextHolder.setContext(this);
    }

    public void needAProgram() {
        if (program != null) {
            return;
        }
        String vertexPath = "assets/simple.vert";
        String fragmentPath = "assets/simple.frag";
        Shader vertexShader = Shader.fromPath(vertexPath);
        Shader fragmentShader = Shader.fromPath(fragmentPath);
        program = new Program();
        program.attach(vertexShader);
        program.attach(fragmentShader);
        program.link();


    }

    public Program getProgram() {
        return program;
    }

    public Application createApplication() {
        return new Application();
    }

    public ApplicationConfig getConfig() {
        return config;
    }

    public Logger getLogger(Class<?> clazz) {
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }

    public boolean isOpenGLInitialized() {
        return isOpenGLInitialized;
    }

    void setOpenGLInitialized(boolean initialized) {
        isOpenGLInitialized = initialized;
    }

    public void glInit() {
        setOpenGLInitialized(true);
        GL.createCapabilities();
    }

    public void requestClose() {
        isCloseRequested = true;
    }

    public boolean isCloseRequested() {
        return isCloseRequested;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        if (window == null) {
            throw new RuntimeException("Window is not set");
        }
        return window;
    }
}
