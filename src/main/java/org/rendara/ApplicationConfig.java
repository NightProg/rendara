package org.rendara;


public class ApplicationConfig {
    boolean logger = false;
    boolean assetServer = false;
    boolean debugLogging = false;
    String customAssetPath = "assets";
    OpenGLVersion openGLVersion = new OpenGLVersion(3, 3);

    public ApplicationConfig() {
    }

    public void useLogger() {
        this.logger = true;
    }

    public void disableLogger() {
        this.logger = false;
    }

    public void useAssetServer(String customAssetPath) {
        this.assetServer = true;
        this.customAssetPath = customAssetPath;
    }

    public void useAssetServer() {
        this.assetServer = true;
    }

    public void enableDebugLogging() {
        this.debugLogging = true;
    }

    public String getCustomAssetPath() {
        return customAssetPath;
    }

    public boolean shouldUseLogger() {
        return logger;
    }

    public boolean isDebugLoggingEnabled() {
        return debugLogging;
    }

    public OpenGLVersion getOpenGLVersion() {
        return openGLVersion;
    }

    public void setOpenGLVersion(OpenGLVersion version) {
        openGLVersion = version;
    }

    public void setOpenGLVersion(int major, int minor) {
        openGLVersion = new OpenGLVersion(major, minor);
    }

}

