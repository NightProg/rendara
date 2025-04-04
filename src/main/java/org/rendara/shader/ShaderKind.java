package org.rendara.shader;

import org.lwjgl.opengl.GL33;

public enum ShaderKind {
    SHADER_KIND_VERTEX,
    SHADER_KIND_FRAGMENT,
    ;

    static ShaderKind fromPath(String path) {
        if (path.endsWith(".vert")) {
            return SHADER_KIND_VERTEX;
        } else if (path.endsWith(".frag")) {
            return SHADER_KIND_FRAGMENT;
        } else {
            return null;
        }
    }

    public int getKind() {
        if (this == SHADER_KIND_VERTEX) {
            return GL33.GL_VERTEX_SHADER;
        } else if (this == SHADER_KIND_FRAGMENT) {
            return GL33.GL_FRAGMENT_SHADER;
        } else {
            return 0;
        }
    }
}
