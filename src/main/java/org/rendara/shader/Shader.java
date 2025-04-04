package org.rendara.shader;

import org.lwjgl.opengl.GL33;
import org.rendara.asset.Asset;
import org.rendara.asset.AssetLoader;
import org.rendara.exception.ShaderCompileError;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Shader implements AssetLoader {
    private int shaderId;

    public static Shader fromPath(String path) {
        ShaderKind kind = ShaderKind.fromPath(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder shader = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                shader.append(line).append("\n");
            }
            reader.close();
            return new Shader(shader.toString(), kind);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Shader fromBuilder(ShaderBuilder builder, ShaderKind kind) {
        return new Shader(builder.build(), kind);
    }

    private Shader(int shaderId) {
        this.shaderId = shaderId;
    }

    public Shader(String shader, ShaderKind kind) {
        this.shaderId = GL33.glCreateShader(kind.getKind());
        GL33.glShaderSource(this.shaderId, shader);

        GL33.glCompileShader(this.shaderId);

        // check for shader compile errors
        if (GL33.glGetShaderi(this.shaderId, GL33.GL_COMPILE_STATUS) == GL33.GL_FALSE) {
            String res = GL33.glGetShaderInfoLog(this.shaderId);
            throw new ShaderCompileError(res);
        }
    }

    public int getShaderId() {
        return shaderId;
    }

    public void use() {
        GL33.glUseProgram(shaderId);
    }

    public void delete() {
        GL33.glDeleteShader(shaderId);
    }

}
