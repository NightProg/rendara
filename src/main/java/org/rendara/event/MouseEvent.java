package org.rendara.event;

public class MouseEvent extends Event {
    public MouseKind kind;
    public double x;
    public double y;

    public MouseEvent(MouseKind kind,  double x, double y) {
        super(EventKind.MOUSE);
        this.kind = kind;
        this.x = x;
        this.y = y;
    }
}
