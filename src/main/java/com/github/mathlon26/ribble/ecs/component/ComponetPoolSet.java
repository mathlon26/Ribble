package com.github.mathlon26.ribble.ecs.component;

/*
Represents a set of componentpools that can easily be retrieved by type
 */

import java.util.HashMap;


public class ComponetPoolSet {
    private final HashMap<Class<? extends Component>, ComponentPool<? extends Component>> pools = new HashMap<>();

    public <T extends Component> ComponentPool<T> addPool(Class<T> type) {
        ComponentPool<T> pool = new ComponentPool<T>();

        pools.put(type, pool);

        return pool;
    }

    public <T extends Component> ComponentPool<T> getPool(Class<T> type) {
        return (ComponentPool<T>) pools.get(type);
    }
}
