package org.rendara.shader;

import org.lwjgl.opengl.GL33;
import org.rendara.math.Mat4;
import org.rendara.math.Vec3;

public class Program {
    private int programId;

    public Program() {
        this.programId = GL33.glCreateProgram();
    }

    public int getProgramId() {
        return programId;
    }

    public void attach(Shader shader) {
        GL33.glAttachShader(programId, shader.getShaderId());
    }

    public void link() {
        GL33.glLinkProgram(programId);
        if (GL33.glGetProgrami(programId, GL33.GL_LINK_STATUS) == GL33.GL_FALSE) {
            System.out.println(GL33.glGetProgramInfoLog(programId));
            throw new RuntimeException("Program linking failed");
        }
    }

    public void use() {
        GL33.glUseProgram(programId);
    }

    public void setUniform(String name, int value) {
        int location = GL33.glGetUniformLocation(programId, name);
        GL33.glUniform1i(location, value);
    }

    public void setUniform(String name, float value) {
        int location = GL33.glGetUniformLocation(programId, name);
        GL33.glUniform1f(location, value);
    }

    public void setUniform(String name, Vec3 value) {
        int location = GL33.glGetUniformLocation(programId, name);
        GL33.glUniform3f(location, (float) value.x, (float) value.y, (float) value.z);
    }

    public void setUniform(String name, Mat4 value) {
        int location = GL33.glGetUniformLocation(programId, name);
        GL33.glUniformMatrix4fv(location, false, value.toFloatBuffer());
    }
}
