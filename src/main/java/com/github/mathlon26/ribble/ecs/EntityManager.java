package com.github.mathlon26.ribble.ecs;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.ecs.component.ComponentPool;
import com.github.mathlon26.ribble.ecs.component.ComponentPoolSet;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.entity.EntityPool;
import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.ecs.system.SystemManager;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class EntityManager {
    @Getter @Setter
    private EntityPool entityPool;

    @Getter @Setter
    private ComponentPoolSet componentPools;

    @Getter @Setter
    private SystemManager systemManager;

    // long to match your Entity(long id)
    private final AtomicLong idCounter = new AtomicLong(1);

    private static EntityManager s_instance;

    private EntityManager(EntityPool entityPool, ComponentPoolSet componentPools, SystemManager systemManager) {
        this.entityPool = entityPool;
        this.componentPools = componentPools;
        this.systemManager = systemManager;
    }

    private EntityManager() {
        this(new EntityPool(), new ComponentPoolSet(), new SystemManager());
    }

    public static EntityManager getInstance() {
        if (s_instance == null) {
            s_instance = new EntityManager();
        }
        return s_instance;
    }

    public static EntityManager getInstance(EntityPool entityPool, ComponentPoolSet componentPools, SystemManager systemManager) {
        EntityManager em = getInstance();
        em.setEntityPool(entityPool);
        em.setComponentPools(componentPools);
        em.setSystemManager(systemManager);
        s_instance = em;
        return s_instance;
    }

    @Override
    public String toString() {
        return "EntityManager{" +
                "entityPool=" + Objects.toString(entityPool) +
                ", componentPools=" + Objects.toString(componentPools) +
                ", systemManager=" + Objects.toString(systemManager) +
                ", localEntities=" + entityPool.size() +
                '}';
    }

    /**
     * Create a new Entity with a unique id and register it locally.
     */
    public Entity createEntity() {
        long id = idCounter.getAndIncrement();
        Entity e = new Entity(id);
        entityPool.add(e);
        return e;
    }

    /**
     * Remove an entity from the manager's local set.
     */
    public void destroyEntity(Entity toDestroy) {
        if (toDestroy == null) return;
        entityPool.remove(toDestroy);
    }

    /**
     * Get the component of type T attached to the given entity.
     * Uses componentPools.getPool(type).get(entity)
     *
     * @param type   component class
     * @param entity entity
     * @param <T>    component type
     * @return component instance or null
     */
    public <T extends Component> T getComponentFromEntity(Class<T> type, Entity entity) {
        if (componentPools == null || type == null || entity == null) return null;
        ComponentPool<T> pool = componentPools.getPool(type);
        if (pool == null) return null;
        return pool.getComponent(entity);
    }

    /**
     * Get all components of the given type.
     * Uses componentPools.getPool(type).getAll()
     *
     * @param type component class
     * @param <T>  component type
     * @return collection of components (empty if none or unsupported)
     */
    public <T extends Component> Collection<T> getComponentsOfType(Class<T> type) {
        if (componentPools == null || type == null) return Collections.emptyList();
        ComponentPool<T> pool = componentPools.getPool(type);
        if (pool == null) return Collections.emptyList();
        Collection<T> all = pool.getAll();
        return (all != null) ? all : Collections.emptyList();
    }

    /**
     * Update systems by delegating to the SystemManager.
     */
    public void update(double deltatime) {
        if (systemManager == null) return;
        systemManager.updateAll(deltatime);
    }

    /**
     * Expose entities (read-only).
     */
    public Collection<Entity> getAllEntities() {
        return Collections.unmodifiableSet(entityPool.getAll());
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> void addComponentToEntity(T component, Entity entity) {
        Class<T> type = (Class<T>) component.getClass();

        ComponentPool<T> pool = componentPools.getPool(type);
        if (pool == null) {
            componentPools.addPool(type);
            pool = componentPools.getPool(type);
        }

        pool.addComponent(entity, component);
    }

    public <T extends SystemBase> void addSystem(T system) {
        systemManager.addSystem(system);
    }

    public void destroyCurrentSystems() {
        systemManager.destroyAll();
    }

    public void destroyCurrentComponents() {
        componentPools = new ComponentPoolSet();
    }

    public void destroyCurrentEntities() {
        for (Entity e : getAllEntities())
            destroyEntity(e);
    }
}
