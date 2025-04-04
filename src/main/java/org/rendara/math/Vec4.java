package org.rendara.math;

public class Vec4 {
    double x, y, z, w;

    public Vec4() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Vec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4 add(Vec4 other) {
        return new Vec4(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Vec4 sub(Vec4 other) {
        return new Vec4(x - other.x, y - other.y, z - other.z, w - other.w);
    }

    public Vec4 mul(double scalar) {
        return new Vec4(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vec4 div(double scalar) {
        if (scalar == 0) throw new ArithmeticException("Division by zero");
        return new Vec4(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public double dot(Vec4 other) {
        return x * other.x + y * other.y + z * other.z + w * other.w;
    }

    public Vec4 cross(Vec4 other) {
        return new Vec4(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x,
            0
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vec4 normalize() {
        double length = length();
        if (length == 0) return new Vec4(0, 0, 0, 0);
        return this.div(length);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public double[] toArray() {
        return new double[] {x, y, z, w};
    }
}
