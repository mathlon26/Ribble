package com.github.mathlon26.ribble.ecs.system;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.component.Component;

import java.util.Collection;

public abstract class SystemBase {
    public static <T extends Component> Collection<T> getComponentsOfType(Class<T> type) {
        return EntityManager.getInstance().getComponentsOfType(type);
    }

    public abstract void start();
    public abstract void update(double deltatime);
    public abstract void destroy();
}
