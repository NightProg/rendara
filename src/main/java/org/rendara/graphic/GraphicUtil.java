package org.rendara.graphic;

import org.rendara.math.Vec2;
import org.rendara.math.Vec2f;

public class GraphicUtil {
    public static Vec2f[] getUVs(Image image, float x, float y, float width, float height) {
        float uStart = x / image.getWidth();
        float vStart = y / image.getHeight();
        float uEnd = (x + width) / image.getWidth();
        float vEnd = (y + height) / image.getHeight();

        Vec2f[] uvs = new Vec2f[6];
        uvs[0] = new Vec2f(uStart, vStart); // Bottom-left
        uvs[1] = new Vec2f(uEnd, vStart);   // Bottom-right
        uvs[2] = new Vec2f(uEnd, vEnd);     // Top-right
        uvs[3] = new Vec2f(uEnd, vEnd);     // Top-right
        uvs[4] = new Vec2f(uStart, vEnd);   // Top-left
        uvs[5] = new Vec2f(uStart, vStart); // Bottom-left

        return uvs;
    }
}
