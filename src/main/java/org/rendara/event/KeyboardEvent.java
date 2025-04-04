package org.rendara.event;

public class KeyboardEvent extends Event {
    public KeyboardKind kind;
    public KeyboardModifier modifier;
    public KeyboardAction action;


    public KeyboardEvent(KeyboardKind kind, KeyboardModifier modifier, KeyboardAction action) {
        super(EventKind.KEYBOARD);
        this.kind = kind;
        this.modifier = modifier;
        this.action = action;
    }
}
