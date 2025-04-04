package org.rendara.asset;

import org.rendara.ecs.Component;
import org.rendara.ecs.Resource;

public class AssetServer extends Resource {
    private String basePath = "assets";
    public AssetServer(String basePath) {
        if (basePath.startsWith("/")) {
            basePath = basePath.substring(1);
        }
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public  Asset loadAsset(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return new Asset(basePath + "/" + path);
    }
}
