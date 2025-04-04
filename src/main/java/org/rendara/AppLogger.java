package org.rendara;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class AppLogger implements Logger {
    Class<?> clazz;

    Logger logger;

    public AppLogger(Class<?> clazz) {
        this.clazz = clazz;
        this.logger = ContextHolder.getContext().getLogger(clazz);
    }


    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        logger.trace(s);
    }

    @Override
    public void trace(String s, Object o) {
        logger.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        logger.trace(s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        logger.trace(s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        logger.trace(s, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String s) {
        logger.trace(marker, s);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        logger.trace(marker, s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        logger.trace(marker, s, o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        logger.trace(marker, s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        logger.trace(marker, s, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return ContextHolder
                .getContext()
                .getConfig()
                .isDebugLoggingEnabled() &&
                ContextHolder
                        .getContext()
                        .getConfig()
                        .shouldUseLogger();
    }

    public boolean isLoggingEnabled() {
        return ContextHolder
                .getContext()
                .getConfig()
                .shouldUseLogger();
    }

    @Override
    public void debug(String s) {
        if (isLoggingEnabled()) {
            logger.debug(s);
        }

    }

    @Override
    public void debug(String s, Object o) {
        if (isLoggingEnabled()) {
            logger.debug(s, o);
        }
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.debug(s, o, o1);
        }
    }

    @Override
    public void debug(String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.debug(s, objects);
        }
    }

    @Override
    public void debug(String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.debug(s, throwable);
        }
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker) && isLoggingEnabled();
    }

    @Override
    public void debug(Marker marker, String s) {
        if (isLoggingEnabled()) {
            logger.debug(marker, s);
        }
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        if (isLoggingEnabled()) {
            logger.debug(marker, s, o);
        }
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.debug(marker, s, o, o1);
        }
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.debug(marker, s, objects);
        }
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.debug(marker, s, throwable);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled() && isLoggingEnabled();
    }

    @Override
    public void info(String s) {
        if (isLoggingEnabled()) {
            logger.info(s);
        }
    }

    @Override
    public void info(String s, Object o) {
        if (isLoggingEnabled()) {
            logger.info(s, o);
        }
    }

    @Override
    public void info(String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.info(s, o, o1);
        }
    }

    @Override
    public void info(String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.info(s, objects);
        }
    }

    @Override
    public void info(String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.info(s, throwable);
        }
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker) && isLoggingEnabled();
    }

    @Override
    public void info(Marker marker, String s) {
        if (isLoggingEnabled()) {
            logger.info(marker, s);
        }
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        if (isLoggingEnabled()) {
            logger.info(marker, s, o);
        }
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.info(marker, s, o, o1);
        }
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.info(marker, s, objects);
        }
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.info(marker, s, throwable);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled() && isLoggingEnabled();
    }

    @Override
    public void warn(String s) {
        if (isLoggingEnabled()) {
            logger.warn(s);
        }
    }

    @Override
    public void warn(String s, Object o) {
        if (isLoggingEnabled()) {
            logger.warn(s, o);
        }
    }

    @Override
    public void warn(String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.warn(s, objects);
        }
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.warn(s, o, o1);
        }
    }

    @Override
    public void warn(String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.warn(s, throwable);
        }
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker) && isLoggingEnabled();
    }

    @Override
    public void warn(Marker marker, String s) {
        if (isLoggingEnabled()) {
            logger.warn(marker, s);
        }
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        if (isLoggingEnabled()) {
            logger.warn(marker, s, o);
        }
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.warn(marker, s, o, o1);
        }
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.warn(marker, s, objects);
        }
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.warn(marker, s, throwable);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled() && isLoggingEnabled();
    }

    @Override
    public void error(String s) {
        if (isLoggingEnabled()) {
            logger.error(s);
        }
    }

    @Override
    public void error(String s, Object o) {
        if (isLoggingEnabled()) {
            logger.error(s, o);
        }
    }

    @Override
    public void error(String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.error(s, o, o1);
        }
    }

    @Override
    public void error(String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.error(s, objects);
        }
    }

    @Override
    public void error(String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.error(s, throwable);
        }
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker) && isLoggingEnabled();
    }

    @Override
    public void error(Marker marker, String s) {
        if (isLoggingEnabled()) {
            logger.error(marker, s);
        }
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        if (isLoggingEnabled()) {
            logger.error(marker, s, o);
        }
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        if (isLoggingEnabled()) {
            logger.error(marker, s, o, o1);
        }
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        if (isLoggingEnabled()) {
            logger.error(marker, s, objects);
        }
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        if (isLoggingEnabled()) {
            logger.error(marker, s, throwable);
        }
    }
}
