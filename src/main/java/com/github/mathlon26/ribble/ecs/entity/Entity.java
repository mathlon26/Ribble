package com.github.mathlon26.ribble.ecs.entity;

import lombok.Getter;

public class Entity {
    @Getter
    private long id;

    public Entity(long id) {
        this.id = id;
    }
}
