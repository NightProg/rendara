package org.rendara.graphic;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.rendara.ContextHolder;
import org.rendara.World;
import org.rendara.ecs.Entity;
import org.rendara.ecs.ShouldBeUpdated;
import org.rendara.math.Mat4;
import org.rendara.math.Vec3;
import org.rendara.math.Vec4;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@ShouldBeUpdated
public class Mesh extends Entity {
    private final int vaoId;
    private final int vboId;
    private final int iboId;
    private final int vertexCount;

    Mesh(int vaoId, int vboId, int iboId, int vertexCount) {
        this.vaoId = vaoId;
        this.vboId = vboId;
        this.iboId = iboId;
        this.vertexCount = vertexCount;
    }

    public Mesh(float[] vertices, int[] indices) {
        this.vaoId = GL33.glGenVertexArrays();
        this.vboId = GL33.glGenBuffers();
        this.iboId = GL33.glGenBuffers();
        this.vertexCount = indices.length;
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertices, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 8 * Float.BYTES, 0);
        GL33.glEnableVertexAttribArray(0);
        GL33.glVertexAttribPointer(1, 3, GL33.GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        GL33.glEnableVertexAttribArray(1);
        GL33.glVertexAttribPointer(2, 2, GL33.GL_FLOAT, false, 8 * Float.BYTES, 6 * Float.BYTES);
        GL33.glEnableVertexAttribArray(2);
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, iboId);
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, indices, GL33.GL_STATIC_DRAW);
        GL33.glBindVertexArray(0);
        GL33.glBindVertexArray(vaoId);
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices).flip();
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertexBuffer, GL33.GL_STATIC_DRAW);

        IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
        indexBuffer.put(indices).flip();

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, iboId);
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL33.GL_STATIC_DRAW);

        int stride = 8 * Float.BYTES;
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, stride, 0); // Position
        GL33.glEnableVertexAttribArray(0);

        GL33.glVertexAttribPointer(1, 3, GL33.GL_FLOAT, false, stride, 3 * Float.BYTES); // Normale
        GL33.glEnableVertexAttribArray(1);

        GL33.glVertexAttribPointer(2, 2, GL33.GL_FLOAT, false, stride, 6 * Float.BYTES); // UV
        GL33.glEnableVertexAttribArray(2);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
        GL33.glBindVertexArray(0);

        GL33.glBindVertexArray(vaoId);
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertices, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 5 * Float.BYTES, 0);
        GL33.glEnableVertexAttribArray(0);
        GL33.glVertexAttribPointer(1, 2, GL33.GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        GL33.glEnableVertexAttribArray(1);
    }

    @Override
    public void render(World world) {

        ContextHolder.getContext().needAProgram();
        ContextHolder.getContext().getProgram().use();
        if (hasComponent(Transform.class)) {
            Transform transform = getComponent(Transform.class);
            Mat4 model = Mat4.identity()
                            .translate(transform.getPosition())
                            .rotate(transform.getRotation());
            ContextHolder.getContext().getProgram().setUniform("model", model);
        }
        Mat4 model = Mat4.identity();
        ContextHolder.getContext().getProgram().setUniform("model", model.translate(
                new Vec3(0.0f, 0.0f, 0.0f)
        ));
        if (hasComponent(Texture.class)) {
            Texture texture = getComponent(Texture.class);
            GL33.glActiveTexture(GL33.GL_TEXTURE0);
            GL33.glBindTexture(GL33.GL_TEXTURE_2D, texture.getOpenGLId());
        }

        GL33.glBindVertexArray(vaoId);

        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertexCount);



    }
}
