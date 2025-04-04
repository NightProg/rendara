package org.rendara.graphic;

import org.rendara.ecs.Component;
import org.rendara.math.Vec3;

public class Transform extends Component {
    private Vec3 position;
    private Vec3 rotation;

    public Transform(Vec3 position, Vec3 rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Transform(double x, double y, double z, double rx, double ry, double rz) {
        this.position = new Vec3(x, y, z);
        this.rotation = new Vec3(rx, ry, rz);
    }

    public Transform(double x, double y, double z) {
        this.position = new Vec3(x, y, z);
        this.rotation = new Vec3(0, 0, 0);
    }

    public Transform(Vec3 position) {
        this.position = position;
        this.rotation = new Vec3(0, 0, 0);
    }

    public Transform() {
        this.position = new Vec3(0, 0, 0);
        this.rotation = new Vec3(0, 0, 0);
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getRotation() {
        return rotation;
    }

    public void setRotation(Vec3 rotation) {
        this.rotation = rotation;
    }

    public void translate(Vec3 translation) {
        position = position.add(translation);
    }

    public void rotate(Vec3 rotation) {
        this.rotation = this.rotation.add(rotation);
    }

    public void rotateX(float angle) {
        rotation = rotation.add(new Vec3(angle, 0, 0));
    }

    public void rotateY(float angle) {
        rotation = rotation.add(new Vec3(0, angle, 0));
    }

    public void rotateZ(float angle) {
        rotation = rotation.add(new Vec3(0, 0, angle));
    }

    public void rotateXDeg(float angle) {
        rotation = rotation.add(new Vec3(angle, 0, 0));
    }

    public void rotateYDeg(float angle) {
        rotation = rotation.add(new Vec3(0, angle, 0));
    }

    public void rotateZDeg(float angle) {
        rotation = rotation.add(new Vec3(0, 0, angle));
    }


}
