package com.github.mathlon26.ribble.core.time;

import com.github.mathlon26.ribble.core.Config;
import lombok.Getter;

public class GameTime {
    @Getter
    private static double lastTime;
    @Getter
    private static double deltaTime;
    @Getter
    private static double time;

    private static double frameTime;
    @Getter
    private static double startTime;
    @Getter
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

    public static double getFrameTime() { return 1.0 / Config.get("targetFPS", Integer.class); }

    private static double getCurrentTime() {
        return System.nanoTime() / 1_000_000_000.0; // convert ns to seconds
    }
}
