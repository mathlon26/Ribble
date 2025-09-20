package com.github.mathlon26.ribble.ecs.entity;

public class GameObject extends Entity{
    private boolean isActive;

    public GameObject(long id) {
        super(id);
        this.isActive = false;
    }
}
