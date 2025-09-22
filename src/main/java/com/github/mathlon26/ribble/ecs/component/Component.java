package com.github.mathlon26.ribble.ecs.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class Component{
    @Getter
    @Setter
    private boolean isActive = true;
}
