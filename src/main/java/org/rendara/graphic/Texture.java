package org.rendara.graphic;

import org.lwjgl.opengl.GL33;
import org.rendara.ecs.Component;

public class Texture  extends Component {
    private int openglId;

    private Texture(int openglId) {
        this.openglId = openglId;
    }

    public Texture(Image image) {
        int id = GL33.glGenTextures();
        GL33.glBindTexture(GL33.GL_TEXTURE_2D, id);
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_LINEAR);
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_LINEAR);
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_NEAREST);
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_NEAREST);

        GL33.glTexImage2D(GL33.GL_TEXTURE_2D, 0, GL33.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL33.GL_RGBA, GL33.GL_UNSIGNED_BYTE, image.getData());

        GL33.glGenerateMipmap(GL33.GL_TEXTURE_2D);

        int error = GL33.glGetError();
        if (error != GL33.GL_NO_ERROR) {
            System.out.println("OpenGL error: " + error);
            throw new RuntimeException("OpenGL error: " + error);
        }
        this.openglId = id;
    }

    public int getOpenGLId() {
        return openglId;
    }
}
