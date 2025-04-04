package org.rendara.ecs;

public class Resource {
    private static int id = 0;

    private int resourceId;


    public Resource() {
        id++;
        resourceId = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
