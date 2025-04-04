package org.rendara.event;

import org.rendara.ecs.Resource;

public class Event extends Resource {
    public EventKind kind;

    public Event(EventKind kind) {
        this.kind = kind;
    }

}
