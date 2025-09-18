package com.github.mathlon26.ribble_deprecated.util;

public class Time {
    public static long timeStarted = System.nanoTime();

    public static double getElapsedTime() { return (Double)((System.nanoTime() - timeStarted) * 1E-9);}

}
