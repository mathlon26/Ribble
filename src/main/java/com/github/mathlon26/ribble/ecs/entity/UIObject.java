package com.github.mathlon26.ribble.ecs.entity;

public class UIObject extends Entity{
    private boolean isShown;
    public UIObject(long id) {
        super(id);
        this.isShown = false;
    }
}
