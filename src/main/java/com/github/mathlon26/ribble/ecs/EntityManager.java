package com.github.mathlon26.ribble.ecs;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.ecs.component.ComponentPool;
import com.github.mathlon26.ribble.ecs.component.ComponetPoolSet;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.entity.EntityPool;
import com.github.mathlon26.ribble.ecs.system.SystemManager;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collection;

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
    private ComponetPoolSet componentPools;

    @Getter @Setter
    private SystemManager systemManager;

    private final AtomicInteger idCounter = new AtomicInteger(1);

    private static EntityManager s_instance;

    public EntityManager() {
        systemManager = new SystemManager();
        componentPools = new ComponetPoolSet();
        entityPool = new EntityPool();
    }

    public EntityManager(EntityPool entityPool, ComponetPoolSet componentPools, SystemManager systemManager) {
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

    public static <T extends Component> T getComponent(Entity entity, Class<T> type) {
        return getInstance().getComponentFor(entity, type);
    }

    public static <T extends Component> Collection<T> getComponents(Class<T> type)
    {
        return getInstance().getComponentsInst(type);
    }


    public static <T extends Component> void addComponent(Entity entity, T component)
    {
        getInstance().addComponentTo(entity, component);
    }



    public <T extends Component> T getComponentFor(Entity entity, Class<T> type) {
        if (entity == null) {return null;}
        if (componentPools == null) {return null;}
        // get the component from the correct pool
        ComponentPool<T> pool = componentPools.getPool(type);
        if(pool == null){return null;}
        return pool.getComponent(entity);
    }
    // Instance version of getComponents
    public <T extends Component> Collection<T> getComponentsInst(Class<T> type)
    {
        ComponentPool<T> pool = componentPools.getPool(type);

        if (pool == null) {
            ExceptionHandler.raise(IllegalArgumentException.class, "No Component pool of type: " + type.getName());
            return null;
        }
        return pool.getComponents();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> void addComponentTo(Entity entity, T component)
    {
        if(componentPools == null) {ExceptionHandler.raise(NullPointerException.class, "Initialisation went wrong!");}

        Class<T> type = (Class<T>) component.getClass();
        ComponentPool<T> pool = componentPools.getPool(type);

        // add a new pool if it does not exist
        if(pool == null)
        {
            pool = componentPools.addPool(type);
        }
        pool.addComponent(entity, component);
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
