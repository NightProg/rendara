package org.rendara.asset;

import org.rendara.ecs.Component;
import org.rendara.graphic.Image;
import org.rendara.shader.Shader;

import java.io.File;

public class Asset {
    private static int assetId = 0;
    private String path;
    private AssetKind kind;
    private AssetLoader data;

    public Asset(String path) {
        assetId++;
        this.path = path;
        this.kind = AssetKind.fromPath(path);
        if (kind == AssetKind.ASSET_KIND_IMAGE) {
            this.data = Image.fromPath(path);
        } else if (kind == AssetKind.ASSET_KIND_SHADER) {
            this.data = Shader.fromPath(path);
        } else {
            this.data = null;
        }
    }

    public int getAssetId() {
        return assetId;
    }

    public String getPath() {
        return path;
    }

    public Image getImage() {
        if (kind == AssetKind.ASSET_KIND_IMAGE) {
            return (Image) data;
        }
        return null;
    }
}
