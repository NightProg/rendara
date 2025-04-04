package org.rendara.graphic;

import org.rendara.ecs.Component;

public class Window extends Component {
    private int width;
    private int height;
    private String title;

    private long windowId;

    public Window(int width, int height, String title) {
        super();
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void setWindowId(long windowId) {
        this.windowId = windowId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindowId() {
        return windowId;
    }
}
