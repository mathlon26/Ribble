package com.github.mathlon26.ribble.ecs.entity;

import com.github.mathlon26.ribble.ecs.EntityManager;
import lombok.Getter;

public class Entity {
    @Getter
    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {
        this.id = EntityManager.getInstance().createEntity().getId();
    }
}
