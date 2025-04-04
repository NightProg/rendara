package org.rendara;

public class OpenGLVersion {
    private int major;
    private int minor;

    public OpenGLVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }
}
