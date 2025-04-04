package org.rendara;

import org.rendara.exception.ContextNotInThread;

public class ContextHolder {
    private static final ThreadLocal<Context> contextHolder = new ThreadLocal<>();

    public static void setContext(Context context) {
        contextHolder.set(context);
    }

    public static Context getContext() {
        Context context = contextHolder.get();
        if (context == null) {
            throw new ContextNotInThread();
        }
        return context;
    }

}
