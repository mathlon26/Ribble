package com.github.mathlon26.ribble.ecs.system;

public abstract class SystemBase {
    public abstract void start();
    public abstract void update(double deltatime);
    public abstract void destroy();
}
