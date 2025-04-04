package org.rendara.math;

public class Vec3 {
    public double x, y, z;

    public Vec3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(x + other.x, y + other.y, z + other.z);
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(x - other.x, y - other.y, z - other.z);
    }

    public Vec3 mul(double scalar) {
        return new Vec3(x * scalar, y * scalar, z * scalar);
    }

    public Vec3 div(double scalar) {
        if (scalar == 0) throw new ArithmeticException("Division by zero");
        return new Vec3(x / scalar, y / scalar, z / scalar);
    }

    public double dot(Vec3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vec3 cross(Vec3 other) {
        return new Vec3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }


    public Vec3 normalize() {
        double len = length();
        if (len == 0) throw new ArithmeticException("Normalization of zero vector");
        return new Vec3(x / len, y / len, z / len);
    }
}
