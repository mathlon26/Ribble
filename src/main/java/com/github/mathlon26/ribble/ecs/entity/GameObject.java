package com.github.mathlon26.ribble.ecs.entity;


import com.github.mathlon26.ribble.managers.EntityManager;
import com.github.mathlon26.ribble.ecs.component.Component;

import java.util.List;

public class GameObject extends Entity{
    private boolean isActive;

    protected List<Component> m_components;

    public GameObject() {
        super();
        this.isActive = false;
    }

    public <T extends Component> void addComponent(T component) {
        EntityManager.getInstance().addComponentToEntity(component, this);
    }
}
