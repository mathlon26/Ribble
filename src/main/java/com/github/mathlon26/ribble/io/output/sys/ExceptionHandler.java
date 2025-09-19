package com.github.mathlon26.ribble.io.output.sys;

public class ExceptionHandler {
    public static <T extends Exception> void raise(Class<T> exceptionClass, String message) throws T {
        Logger.getInstance().raise(exceptionClass, message);
    }

    private ExceptionHandler() {}
}
