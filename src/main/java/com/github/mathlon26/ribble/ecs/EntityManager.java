package com.github.mathlon26.ribble.ecs;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.ecs.component.ComponentPool;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.entity.EntityPool;
import com.github.mathlon26.ribble.ecs.system.SystemManager;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple EntityManager façade for creating entities and retrieving components.
 * <p>
 * Two usage styles are supported:
 * 1) Instance style:
 *    EntityManager mgr = new EntityManager(...);
 *    mgr.createEntity();
 *    mgr.getComponent(entity);
 *
 * 2) Static convenience style:
 *    EntityManager.setInstance(mgr);
 *    EntityManager.createEntity();
 *    EntityManager.getComponent(entity);
 *
 * The getComponent methods use reflection fallbacks to work with different ComponentPool APIs
 * (e.g. get(Entity), get(int), getComponent(Entity), getForEntity(...)).
 */
public class EntityManager {
    @Getter @Setter
    private EntityPool entityPool;

    @Getter @Setter
    private List<ComponentPool<?>> componentPools;

    @Getter @Setter
    private SystemManager systemManager;

    private final AtomicInteger idCounter = new AtomicInteger(1);

    private static EntityManager s_instance;

    public EntityManager() {
    }

    public EntityManager(EntityPool entityPool, List<ComponentPool<?>> componentPools, SystemManager systemManager) {
        this.entityPool = entityPool;
        this.componentPools = componentPools;
        this.systemManager = systemManager;
    }

    public static void setInstance(EntityManager instance) {
        s_instance = instance;
    }

    private static EntityManager getInstance() {
        if (s_instance == null) {
            s_instance = new EntityManager();
        }
        return s_instance;
    }

    public static Entity createEntity() {
        return getInstance().createEntityInstance();
    }

    public Entity createEntityInstance() {
        int id = idCounter.getAndIncrement();
        Entity e = new Entity(id);

        if (entityPool != null) {
            try {
                // prefer an "add" method if present
                Method addMethod = entityPool.getClass().getMethod("add", Entity.class);
                addMethod.invoke(entityPool, e);
            } catch (NoSuchMethodException nsme) {
                // try an "add" with generic type (some implementations might name it differently)
                try {
                    Method addMethod = entityPool.getClass().getMethod("add", Object.class);
                    addMethod.invoke(entityPool, e);
                } catch (Exception ignored) {
                    // last resort: try to call a commonly used "register" or "create" method
                    try {
                        Method reg = entityPool.getClass().getMethod("register", Entity.class);
                        reg.invoke(entityPool, e);
                    } catch (Exception ignored2) {
                        // give up silently — entity still returned to caller
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException ignored) {
                // ignore; still return entity
            }
        }

        return e;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> T getComponent(Entity entity) {
        return (T) getInstance().getComponentFor(entity);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponentFor(Entity entity) {
        if (entity == null) return null;
        if (componentPools == null || componentPools.isEmpty()) return null;

        // Try direct call if ComponentPool declares get(Entity)
        for (ComponentPool<?> pool : componentPools) {
            if (pool == null) continue;

            // 1) Try common signature pool.get(Entity)
            try {
                Method m = pool.getClass().getMethod("get", Entity.class);
                Object res = m.invoke(pool, entity);
                if (res != null) return (T) res;
            } catch (NoSuchMethodException ignored) {
            } catch (IllegalAccessException | InvocationTargetException ignored) {
            }

            // 2) Try pool.get(int entityId)
            int entityId = extractEntityId(entity);
            if (entityId >= 0) {
                try {
                    Method m = pool.getClass().getMethod("get", int.class);
                    Object res = m.invoke(pool, entityId);
                    if (res != null) return (T) res;
                } catch (NoSuchMethodException ignored) {
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                }
            }

            // 3) Try alternate names with Entity parameter
            String[] altNames = {"getComponent", "getForEntity", "getByEntity", "getComponentForEntity", "find", "findByEntity"};
            for (String name : altNames) {
                try {
                    Method m = pool.getClass().getMethod(name, Entity.class);
                    Object res = m.invoke(pool, entity);
                    if (res != null) return (T) res;
                } catch (NoSuchMethodException ignored) {
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                }
            }

            // 4) Try alternate names with int parameter
            for (String name : altNames) {
                try {
                    Method m = pool.getClass().getMethod(name, int.class);
                    Object res = m.invoke(pool, entityId);
                    if (res != null) return (T) res;
                } catch (NoSuchMethodException ignored) {
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                }
            }
        }

        // not found
        return null;
    }

    private int extractEntityId(Entity entity) {
        if (entity == null) return -1;
        try {
            // prefer a getId() method if present
            Method m = entity.getClass().getMethod("getId");
            Object o = m.invoke(entity);
            if (o instanceof Number) {
                return ((Number) o).intValue();
            }
        } catch (NoSuchMethodException ignored) {
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }

        return -1;
    }

    /**
     * Convenience: call systemManager update. Attempts to call common update method names.
     */
    public void updateSystems() {
        if (systemManager == null) return;

        try {
            Method m = systemManager.getClass().getMethod("update");
            m.invoke(systemManager);
            return;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }

        try {
            Method m2 = systemManager.getClass().getMethod("updateAll");
            m2.invoke(systemManager);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
    }

    @Override
    public String toString() {
        return "EntityManager{" +
                "entityPool=" + Objects.toString(entityPool) +
                ", componentPools=" + Objects.toString(componentPools) +
                ", systemManager=" + Objects.toString(systemManager) +
                '}';
    }
}
