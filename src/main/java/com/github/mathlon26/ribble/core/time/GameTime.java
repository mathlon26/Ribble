package com.github.mathlon26.ribble.core.time;

public class GameTime {
    private static double lastTime;
    private static double deltaTime;
    private static double time;

    public static void init() {
        lastTime = getCurrentTime();
        deltaTime = 0;
        time = 0;
    }

    public static void update() {
        double now = getCurrentTime();
        deltaTime = now - lastTime;
        lastTime = now;
        time += deltaTime;
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static double getTime() {
        return time;
    }

    private static double getCurrentTime() {
        return System.nanoTime() / 1_000_000_000.0; // convert ns to seconds
    }
}
