package org.rendara.exception;

public class NotInitialized extends RuntimeException {
    public NotInitialized(String app) {
        super("The application " + app + " has not been initialized.");
    }

}
