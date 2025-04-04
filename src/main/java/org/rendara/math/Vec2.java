package org.rendara.math;

public class Vec2 {
    public double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(x + other.x, y + other.y);
    }

    public Vec2 sub(Vec2 other) {
        return new Vec2(x - other.x, y - other.y);
    }

    public Vec2 mul(double scalar) {
        return new Vec2(x * scalar, y * scalar);
    }

    public Vec2 div(double scalar) {
        return new Vec2(x / scalar, y / scalar);
    }

    public double dot(Vec2 other) {
        return x * other.x + y * other.y;
    }

    public double cross(Vec2 other) {
        return x * other.y - y * other.x;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 normalize() {
        double len = length();
        return new Vec2(x / len, y / len);
    }

    public Vec2 rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vec2(x * cos - y * sin, x * sin + y * cos);
    }

    public Vec2 project(Vec2 other) {
        return other.mul(dot(other) / other.dot(other));
    }

    public Vec2 reflect(Vec2 normal) {
        return normal.mul(2 * dot(normal)).sub(this);
    }

    public Vec2 lerp(Vec2 other, double t) {
        return new Vec2(x + (other.x - x) * t, y + (other.y - y) * t);
    }

    public Vec2 clone() {
        return new Vec2(x, y);
    }

}
