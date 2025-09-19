package com.github.mathlon26.ribble.core.time;

import com.github.mathlon26.ribble.core.Config;

public class GameTime {
    private static double lastTime;
    private static double deltaTime;
    private static double time;
    private static double frameTime;
    private static double startTime;
    private static double elapsedTime;

    public static void init() {
        lastTime = getCurrentTime();
        deltaTime = 0;
        time = 0;
        startTime = getCurrentTime();
        elapsedTime = 0;
        frameTime = 1.0 / Config.get("targetFPS", Integer.class);
    }

    public static void update() {
        double now = getCurrentTime();
        deltaTime = now - lastTime;
        lastTime = now;
        time += deltaTime;
        elapsedTime = now - startTime;
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static double getTime() {
        return time;
    }

    public static double getElapsedTime() {
        return elapsedTime;
    }

    public static double getFrameTime() {
        return frameTime;
    }

    private static double getCurrentTime() {
        return System.nanoTime() / 1_000_000_000.0; // convert ns to seconds
    }
}
