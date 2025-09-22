package com.github.mathlon26.ribble.io.input.listeners;

import com.github.mathlon26.ribble.core.Engine;
import com.github.mathlon26.ribble.math.Vector2D;
import lombok.Getter;

public class WindowListener {
    private static WindowListener instance;

    private static Vector2D size = new Vector2D();

    private WindowListener() { }

    public static WindowListener getInstance() {
        if (instance == null) {
            instance = new WindowListener();
        }
        return instance;
    }

    public Vector2D getSize() {
        return size;
    }

    public static void windowSizeCallback(long window, int width, int height) {
        size.setValues(width, height);
        Engine.getInstance().getWindow().setSize(width, height);
    }
}
