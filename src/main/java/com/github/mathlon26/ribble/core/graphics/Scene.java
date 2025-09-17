package com.github.mathlon26.ribble.core.graphics;

import com.github.mathlon26.ribble.GameApplication;
import com.github.mathlon26.ribble.math.Vec4D;

public abstract class Scene {

    public Scene() {}

    public abstract void update(double dt);
    public abstract void render();
}
