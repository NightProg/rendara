package org.rendara.exception;

public class ShaderCompileError extends RuntimeException {
    public ShaderCompileError(String message) {
        super("Shader compile error: " + message);
    }
}
