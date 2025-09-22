package com.github.mathlon26.ribble.ecs.entity;

import com.github.mathlon26.ribble.managers.EntityManager;
import lombok.Getter;
import lombok.Setter;

public class Entity {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private boolean isActive = true;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {
        this.id = EntityManager.getInstance().createEntity().getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Entity other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
