package com.github.mathlon26.ribble.ecs.entity;

import lombok.Getter;
import lombok.Setter;

public class Entity {
    @Getter
    @Setter
    private long id;

    public Entity(long id) {
        this.id = id;
    }
}
