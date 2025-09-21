package com.github.mathlon26.ribble.ecs.component;

import java.util.HashMap;


public class ComponentPoolSet {
    private final HashMap<Class<? extends Component>, ComponentPool<? extends Component>> pools = new HashMap<>();

    public <T extends Component> void addPool(Class<T> type) {
        ComponentPool<T> pool = new ComponentPool<T>();

        pools.put(type, pool);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> ComponentPool<T> getPool(Class<T> type) {
        return (ComponentPool<T>) pools.get(type);
    }
}
