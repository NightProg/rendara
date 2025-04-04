package org.rendara.graphic;

import org.lwjgl.opengl.GL33;
import org.rendara.ContextHolder;
import org.rendara.World;
import org.rendara.ecs.AsEntity;
import org.rendara.ecs.Component;
import org.rendara.ecs.Entity;
import org.rendara.math.Mat4;
import org.rendara.math.Vec3;

public class Camera extends Component implements AsEntity {

    private Vec3 position;
    private Vec3 up;
    private Vec3 front;
    private Vec3 right;
    private Vec3 worldUp;
    private float yaw;
    private float pitch;
    private float speed;
    private float lastX;
    private float lastY;
    private boolean firstMouse;

    public Camera(Vec3 position, Vec3 up, float yaw, float pitch) {
        this.position = position;
        this.up = up;
        this.yaw = yaw;
        this.pitch = pitch;
        this.front = new Vec3(0.0f, 0.0f, -1.0f);
        this.worldUp = new Vec3(0.0f, 1.0f, 0.0f);
        this.speed = 2.5f;
        this.updateCameraVectors();
        this.lastX = ContextHolder.getContext().getWindow().getWidth() / 2.0f;
        this.lastY = ContextHolder.getContext().getWindow().getHeight() / 2.0f;
        this.firstMouse = true;
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getUp() {
        return up;
    }

    public void setUp(Vec3 up) {
        this.up = up;
    }

    public Vec3 getFront() {
        return front;
    }

    public void setFront(Vec3 front) {
        this.front = front;
    }

    public Vec3 getRight() {
        return right;
    }

    public void setRight(Vec3 right) {
        this.right = right;
    }

    public Vec3 getWorldUp() {
        return worldUp;
    }

    public void setWorldUp(Vec3 worldUp) {
        this.worldUp = worldUp;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void updateCameraVectors() {
        Vec3 front = new Vec3();
        front.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.normalize();
        this.front = front;
        this.right = front.cross(worldUp).normalize();
        this.up = right.cross(front).normalize();
    }

    public void processMouseMovement(float x, float y, boolean constrainPitch) {
        if (firstMouse) {
            lastX = x;
            lastY = y;
            firstMouse = false;
        }

        float xoffset = x - lastX;
        float yoffset = lastY - y;
        lastX = x;
        lastY = y;

        yaw += xoffset;
        pitch += yoffset;

        if (constrainPitch) {
            if (pitch > 89.0f) {
                pitch = 89.0f;
            }
            if (pitch < -89.0f) {
                pitch = -89.0f;
            }
        }

        updateCameraVectors();
    }

    public void processKeyboard(CameraMovement direction, float deltaTime) {
        float velocity = speed * deltaTime;
        if (direction == CameraMovement.FORWARD) {
            position = position.add(front.mul(velocity));
        }
        if (direction == CameraMovement.BACKWARD) {
            position = position.sub(front.mul(velocity));
        }
        if (direction == CameraMovement.LEFT) {
            position = position.sub(right.mul(velocity));
        }
        if (direction == CameraMovement.RIGHT) {
            position = position.add(right.mul(velocity));
        }
    }

    public Mat4 getViewMatrix() {
        return Mat4.lookAt(position, position.add(front), up);
    }

    @Override
    public CameraEntity asEntity() {
        return new CameraEntity(this);
    }
}
