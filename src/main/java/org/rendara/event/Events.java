package org.rendara.event;

import org.rendara.ecs.Resource;

import java.util.ArrayList;
import java.util.List;

public class Events extends Resource {
    List<Event> events = new ArrayList<>();

    public void add(Event event) {
        events.add(event);
    }

    public Event get() {
        return events.remove(0);
    }

    public Event get(EventKind kind) {
        for (Event event : events) {
            if (event.kind == kind) {
                events.remove(event);
                return event;
            }
        }
        return null;
    }

    public boolean isPressed(KeyboardKind kind) {
        Event event = get(EventKind.KEYBOARD);
        if (event != null) {
            KeyboardEvent keyboardEvent = (KeyboardEvent) event;
            return keyboardEvent.kind == kind && keyboardEvent.action == KeyboardAction.PRESS;
        }
        return false;
    }
    
    public boolean isReleased(KeyboardKind kind) {
        Event event = get(EventKind.KEYBOARD);
        if (event != null) {
            KeyboardEvent keyboardEvent = (KeyboardEvent) event;
            return keyboardEvent.kind == kind && keyboardEvent.action == KeyboardAction.RELEASE;
        }
        return false;
    }
    
    public boolean isPressedLocked(KeyboardKind kind) {
        Event event = get(EventKind.KEYBOARD);
        if (event != null) {
            KeyboardEvent keyboardEvent = (KeyboardEvent) event;
            return keyboardEvent.kind == kind && keyboardEvent.action == KeyboardAction.PRESS;
        }
        return isPressedLocked(kind);
    }

}
