package org.rendara.asset;

public enum AssetKind {
    ASSET_KIND_IMAGE,
    ASSET_KIND_AUDIO,
    ASSET_KIND_VIDEO,
    ASSET_KIND_FONT,
    ASSET_KIND_SHADER,
    ;

    static AssetKind fromPath(String path) {
        if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".bmp")) {
            return ASSET_KIND_IMAGE;
        } else if (path.endsWith(".mp3") || path.endsWith(".wav") || path.endsWith(".ogg")) {
            return ASSET_KIND_AUDIO;
        } else if (path.endsWith(".mp4") || path.endsWith(".avi") || path.endsWith(".mkv")) {
            return ASSET_KIND_VIDEO;
        } else if (path.endsWith(".ttf") || path.endsWith(".otf") || path.endsWith(".woff") || path.endsWith(".woff2")) {
            return ASSET_KIND_FONT;
        } else if (path.endsWith(".vert") || path.endsWith(".frag") || path.endsWith(".glsl")) {
            return ASSET_KIND_SHADER;
        } else {
            return null;
        }
    }
}
