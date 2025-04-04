package org.rendara.event;

import org.lwjgl.glfw.GLFW;

public enum KeyboardModifier {
    SHIFT(GLFW.GLFW_MOD_SHIFT),
    CONTROL(GLFW.GLFW_MOD_CONTROL),
    ALT(GLFW.GLFW_MOD_ALT),
    COMMAND(GLFW.GLFW_MOD_SUPER),
    CAPS_LOCK(GLFW.GLFW_MOD_CAPS_LOCK),
    NUM_LOCK(GLFW.GLFW_MOD_NUM_LOCK),
    ;

    public final int value;

    public static KeyboardModifier fromGLFW(int value) {
        for (KeyboardModifier modifier : values()) {
            if (modifier.value == value) {
                return modifier;
            }
        }
        return null;
    }
    KeyboardModifier(int value) {
        this.value = value;
    }

}
