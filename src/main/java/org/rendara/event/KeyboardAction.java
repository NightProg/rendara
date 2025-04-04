package org.rendara.event;

import org.lwjgl.glfw.GLFW;

public enum KeyboardAction {
    PRESS(GLFW.GLFW_PRESS),
    RELEASE(GLFW.GLFW_RELEASE),
    REPEAT(GLFW.GLFW_REPEAT)
    ;

    public final int value;

    public static KeyboardAction fromGLFW(int value) {
        for (KeyboardAction action : values()) {
            if (action.value == value) {
                return action;
            }
        }
        return null;
    }

    KeyboardAction(int value) {
        this.value = value;
    }
}
