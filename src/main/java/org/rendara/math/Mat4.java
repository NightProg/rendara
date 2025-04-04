package org.rendara.math;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Mat4 {
    Vec4 c0, c1, c2, c3;

    public static Mat4 identity() {
        return new Mat4(
                new Vec4(1, 0, 0, 0),
                new Vec4(0, 1, 0, 0),
                new Vec4(0, 0, 1, 0),
                new Vec4(0, 0, 0, 1)
        );
    }
    public static Mat4 perspective(float fov, float aspect, float near, float far) {
        float tanHalfFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = near - far;

        return new Mat4(
                new Vec4(1.0f / (tanHalfFov * aspect), 0, 0, 0),
                new Vec4(0, 1.0f / tanHalfFov, 0, 0),
                new Vec4(0, 0, (-near - far) / range, 2 * far * near / range),
                new Vec4(0, 0, 1, 0)
        );
    }
    public static Mat4 lookAt(Vec3 position, Vec3 target, Vec3 up) {
        Vec3 zAxis = position.sub(target).normalize();
        Vec3 xAxis = up.cross(zAxis).normalize();
        Vec3 yAxis = zAxis.cross(xAxis).normalize();

        return new Mat4(
                new Vec4(xAxis.x, yAxis.x, zAxis.x, 0),
                new Vec4(xAxis.y, yAxis.y, zAxis.y, 0),
                new Vec4(xAxis.z, yAxis.z, zAxis.z, 0),
                new Vec4(-xAxis.dot(position), -yAxis.dot(position), -zAxis.dot(position), 1)
        );
    }

    public Mat4(Vec4 c0, Vec4 c1, Vec4 c2, Vec4 c3) {
        this.c0 = c0;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    // Matrix addition

    public Mat4 add(Mat4 other) {
        return new Mat4(
                this.c0.add(other.c0),
                this.c1.add(other.c1),
                this.c2.add(other.c2),
                this.c3.add(other.c3)
        );
    }

    // Matrix subtraction

    public Mat4 sub(Mat4 other) {
        return new Mat4(
                this.c0.sub(other.c0),
                this.c1.sub(other.c1),
                this.c2.sub(other.c2),
                this.c3.sub(other.c3)
        );
    }

    // Scalar multiplication
    public Mat4 mul(float scalar) {
        return new Mat4(
                this.c0.mul(scalar),
                this.c1.mul(scalar),
                this.c2.mul(scalar),
                this.c3.mul(scalar)
        );
    }

    // Matrix multiplication
    public Mat4 mul(Mat4 other) {
        Vec4 v0 = new Vec4(), v1 = new Vec4(), v2 = new Vec4(), v3 = new Vec4();
        v0.x = this.c0.x * other.c0.x + this.c1.x * other.c0.y + this.c2.x * other.c0.z + this.c3.x * other.c0.w;
        v0.y = this.c0.y * other.c0.x + this.c1.y * other.c0.y + this.c2.y * other.c0.z + this.c3.y * other.c0.w;
        v0.z = this.c0.z * other.c0.x + this.c1.z * other.c0.y + this.c2.z * other.c0.z + this.c3.z * other.c0.w;
        v0.w = this.c0.w * other.c0.x + this.c1.w * other.c0.y + this.c2.w * other.c0.z + this.c3.w * other.c0.w;

        v1.x = this.c0.x * other.c1.x + this.c1.x * other.c1.y + this.c2.x * other.c1.z + this.c3.x * other.c1.w;
        v1.y = this.c0.y * other.c1.x + this.c1.y * other.c1.y + this.c2.y * other.c1.z + this.c3.y * other.c1.w;
        v1.z = this.c0.z * other.c1.x + this.c1.z * other.c1.y + this.c2.z * other.c1.z + this.c3.z * other.c1.w;
        v1.w = this.c0.w * other.c1.x + this.c1.w * other.c1.y + this.c2.w * other.c1.z + this.c3.w * other.c1.w;

        v2.x = this.c0.x * other.c2.x + this.c1.x * other.c2.y + this.c2.x * other.c2.z + this.c3.x * other.c2.w;
        v2.y = this.c0.y * other.c2.x + this.c1.y * other.c2.y + this.c2.y * other.c2.z + this.c3.y * other.c2.w;
        v2.z = this.c0.z * other.c2.x + this.c1.z * other.c2.y + this.c2.z * other.c2.z + this.c3.z * other.c2.w;
        v2.w = this.c0.w * other.c2.x + this.c1.w * other.c2.y + this.c2.w * other.c2.z + this.c3.w * other.c2.w;

        v3.x = this.c0.x * other.c3.x + this.c1.x * other.c3.y + this.c2.x * other.c3.z + this.c3.x * other.c3.w;
        v3.y = this.c0.y * other.c3.x + this.c1.y * other.c3.y + this.c2.y * other.c3.z + this.c3.y * other.c3.w;
        v3.z = this.c0.z * other.c3.x + this.c1.z * other.c3.y + this.c2.z * other.c3.z + this.c3.z * other.c3.w;
        v3.w = this.c0.w * other.c3.x + this.c1.w * other.c3.y + this.c2.w * other.c3.z + this.c3.w * other.c3.w;

        return new Mat4(v0, v1, v2, v3);
    }

    public Mat4 translate(Vec3 translation) {
        return mul(new Mat4(
                new Vec4(1, 0, 0, 0),
                new Vec4(0, 1, 0, 0),
                new Vec4(0, 0, 1, 0),
                new Vec4(translation.x, translation.y, translation.z, 1)
        ));
    }

    public Mat4 rotate(Vec3 rotation) {
        return rotateX(rotation.x).rotateY(rotation.y).rotateZ(rotation.z);
    }

    public Mat4 rotateX(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return mul(new Mat4(
                new Vec4(1, 0, 0, 0),
                new Vec4(0, cos, -sin, 0),
                new Vec4(0, sin, cos, 0),
                new Vec4(0, 0, 0, 1)
        ));
    }

    public Mat4 rotateY(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return mul(new Mat4(
                new Vec4(cos, 0, sin, 0),
                new Vec4(0, 1, 0, 0),
                new Vec4(-sin, 0, cos, 0),
                new Vec4(0, 0, 0, 1)
        ));
    }

    public Mat4 rotateZ(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return mul(new Mat4(
                new Vec4(cos, -sin, 0, 0),
                new Vec4(sin, cos, 0, 0),
                new Vec4(0, 0, 1, 0),
                new Vec4(0, 0, 0, 1)
        ));
    }

    public Mat4 scale(Vec3 scale) {
        return mul(new Mat4(
                new Vec4(scale.x, 0, 0, 0),
                new Vec4(0, scale.y, 0, 0),
                new Vec4(0, 0, scale.z, 0),
                new Vec4(0, 0, 0, 1)
        ));
    }



    @Override
    public String toString() {
        return "[ " + c0 + "\n" + c1 + "\n" + c2 + "\n" + c3 + " ]";
    }

    public double[] toFlatArray() {
        return new double[] {
            c0.x, c0.y, c0.z, c0.w,
            c1.x, c1.y, c1.z, c1.w,
            c2.x, c2.y, c2.z, c2.w,
            c3.x, c3.y, c3.z, c3.w
        };
    }

    public double[][] to2DArray() {
        return new double[][]{
                {c0.x, c0.y, c0.z, c0.w},
                {c1.x, c1.y, c1.z, c1.w},
                {c2.x, c2.y, c2.z, c2.w},
                {c3.x, c3.y, c3.z, c3.w}
        };
    }

    public FloatBuffer toFloatBuffer() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        buffer.put(new float[] {
                (float) c0.x, (float) c0.y, (float) c0.z, (float) c0.w,
                (float) c1.x, (float) c1.y, (float) c1.z, (float) c1.w,
                (float) c2.x, (float) c2.y, (float) c2.z, (float) c2.w,
                (float) c3.x, (float) c3.y, (float) c3.z, (float) c3.w
        });
        buffer.flip();
        return buffer;
    }
}
