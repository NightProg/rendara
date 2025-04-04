package org.rendara.event;

import org.lwjgl.glfw.GLFW;

public enum KeyboardKind {
    KEY_A(GLFW.GLFW_KEY_A),
    KEY_B(GLFW.GLFW_KEY_B),
    KEY_C(GLFW.GLFW_KEY_C),
    KEY_D(GLFW.GLFW_KEY_D),
    KEY_E(GLFW.GLFW_KEY_E),
    KEY_F(GLFW.GLFW_KEY_F),
    KEY_G(GLFW.GLFW_KEY_G),
    KEY_H(GLFW.GLFW_KEY_H),
    KEY_I(GLFW.GLFW_KEY_I),
    KEY_J(GLFW.GLFW_KEY_J),
    KEY_K(GLFW.GLFW_KEY_K),
    KEY_L(GLFW.GLFW_KEY_L),
    KEY_M(GLFW.GLFW_KEY_M),
    KEY_N(GLFW.GLFW_KEY_N),
    KEY_O(GLFW.GLFW_KEY_O),
    KEY_P(GLFW.GLFW_KEY_P),
    KEY_Q(GLFW.GLFW_KEY_Q),
    KEY_R(GLFW.GLFW_KEY_R),
    KEY_S(GLFW.GLFW_KEY_S),
    KEY_T(GLFW.GLFW_KEY_T),
    KEY_U(GLFW.GLFW_KEY_U),
    KEY_V(GLFW.GLFW_KEY_V),
    KEY_W(GLFW.GLFW_KEY_W),
    KEY_X(GLFW.GLFW_KEY_X),
    KEY_Y(GLFW.GLFW_KEY_Y),
    KEY_Z(GLFW.GLFW_KEY_Z),
    KEY_0(GLFW.GLFW_KEY_0),
    KEY_1(GLFW.GLFW_KEY_1),
    KEY_2(GLFW.GLFW_KEY_2),
    KEY_3(GLFW.GLFW_KEY_3),
    KEY_4(GLFW.GLFW_KEY_4),
    KEY_5(GLFW.GLFW_KEY_5),
    KEY_6(GLFW.GLFW_KEY_6),
    KEY_7(GLFW.GLFW_KEY_7),
    KEY_8(GLFW.GLFW_KEY_8),
    KEY_9(GLFW.GLFW_KEY_9),
    KEY_UNDERSCORE(GLFW.GLFW_KEY_KP_ADD),
    KEY_SPACE(GLFW.GLFW_KEY_SPACE),
    KEY_MINUS(GLFW.GLFW_KEY_MINUS),
    KEY_PLUS(GLFW.GLFW_KEY_EQUAL),
    KEY_LEFT_BRACKET(GLFW.GLFW_KEY_LEFT_BRACKET),
    KEY_RIGHT_BRACKET(GLFW.GLFW_KEY_RIGHT_BRACKET),
    KEY_SEMICOLON(GLFW.GLFW_KEY_SEMICOLON),
    KEY_APOSTROPHE(GLFW.GLFW_KEY_APOSTROPHE),
    KEY_COMMA(GLFW.GLFW_KEY_COMMA),
    KEY_PERIOD(GLFW.GLFW_KEY_PERIOD),
    KEY_SLASH(GLFW.GLFW_KEY_SLASH),
    KEY_BACKSLASH(GLFW.GLFW_KEY_BACKSLASH),

    ;

    public static KeyboardKind fromGLFW(int key) {
        for (KeyboardKind value : values()) {
            if (value.key == key) {
                return value;
            }
        }
        return null;
    }
    int key;

    KeyboardKind(int key) {
        this.key = key;
    }
}
