package org.rendara.exception;

public class InitializationFailed extends RuntimeException {
    public InitializationFailed(String app) {
        super("Failed to initialize " + app);
    }
}
