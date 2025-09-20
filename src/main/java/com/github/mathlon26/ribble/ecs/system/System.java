package com.github.mathlon26.ribble.ecs.system;

public abstract class System {
    public abstract void start();
    public abstract void update();
    public abstract void destroy();
}
