package org.rendara.graphic;

import org.rendara.AppLogger;
import org.rendara.ContextHolder;
import org.rendara.World;
import org.rendara.asset.AssetLoader;
import org.rendara.ecs.Entity;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Image extends Entity implements AssetLoader {
    private BufferedImage image;
    private int[] data;
    private int width, height;
    private static final AppLogger logger = new AppLogger(Image.class);

    public static Image fromPath(String path) {
        Image image = new Image();

        try {
            image.image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            Image.logger.error("Failed to load image from path: {}", path);
        }

        image.width = image.image.getWidth();
        image.height = image.image.getHeight();
        image.image.getRGB(0, 0, image.width, image.height, image.data, 0, image.width);

        return image;
    }

    public static Image fromSource(byte[] source) {
        Image image = new Image();

        try {
            image.image = ImageIO.read(new ByteArrayInputStream(source));
        } catch (Exception e) {
            e.printStackTrace();
            Image.logger.error("Failed to load image from source ");
        }
        return null;
    }

    public void toRGBA() {
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x; // Calcul de l'indice correct
                int a = 255; // Alpha pour opaque
                int b = (pixels[index] & 0xff0000) >> 16;
                int g = (pixels[index] & 0xff00) >> 8;
                int r = (pixels[index] & 0xff);
                data[index] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }


    }

    public int[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void render(World world) {

    }
}
