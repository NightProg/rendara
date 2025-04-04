package org.rendara.exception;

public class ContextNotInThread extends RuntimeException {
    public ContextNotInThread() {
        super("the context not in thread, please set the context first");
    }
}
