package org.rendara.math;

public class Vec2f {
    public float x, y;

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f add(Vec2f other) {
        return new Vec2f(x + other.x, y + other.y);
    }

    public Vec2f sub(Vec2f other) {
        return new Vec2f(x - other.x, y - other.y);
    }

    public Vec2f mul(float scalar) {
        return new Vec2f(x * scalar, y * scalar);
    }

    public Vec2f div(float scalar) {
        return new Vec2f(x / scalar, y / scalar);
    }

    public float dot(Vec2f other) {
        return x * other.x + y * other.y;
    }

    public float cross(Vec2f other) {
        return x * other.y - y * other.x;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vec2f normalize() {
        float len = length();
        return new Vec2f(x / len, y / len);
    }

    public Vec2f rotate(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Vec2f(x * cos - y * sin, x * sin + y * cos);
    }

    public Vec2f project(Vec2f other) {
        return other.mul(dot(other) / other.dot(other));
    }

    public Vec2f reflect(Vec2f normal) {
        return normal.mul(2 * dot(normal)).sub(this);
    }

    public Vec2f lerp(Vec2f other, float t) {
        return new Vec2f(x + (other.x - x) * t, y + (other.y - y) * t);
    }


}
