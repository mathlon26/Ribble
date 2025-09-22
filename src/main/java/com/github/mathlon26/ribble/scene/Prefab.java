package com.github.mathlon26.ribble.scene;

import com.github.mathlon26.ribble.managers.EntityManager;
import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.system.SystemBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Prefab {
    private final List<Component> m_components;
    private Entity m_entity;

    public Prefab() {
        m_components = new ArrayList<>();
        m_entity = null;
        componentSetup();
    }

    protected abstract void componentSetup();
    public abstract void onLoad();
    public abstract void onDestroy();

    protected <T extends Component> void addComponent(T component) {
        m_components.add(component);
    }

    public List<Component> getComponents() {
        return m_components;
    }

    public void setEntity(Entity entity) {
        m_entity = entity;
    }



    public long getEntityID() { return m_entity.getId(); }
}
