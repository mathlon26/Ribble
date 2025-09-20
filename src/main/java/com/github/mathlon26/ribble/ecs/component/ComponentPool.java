package com.github.mathlon26.ribble.ecs.component;

import com.github.mathlon26.ribble.ecs.ObjectPool;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;


import java.util.ArrayList;
import java.util.HashMap;

public class ComponentPool<T extends Component> {
    public ComponentPool() {

    }

    private HashMap<Entity, T> components = new HashMap<Entity, T>();


    public void addComponent(Entity entity, T component)
    {
        /*
        Maybe add support for the same component more than once later
         */

        T currentComponent = components.get(entity);
        if(currentComponent == null)
        {
            components.put(entity, component);
        }else {
            ExceptionHandler.raise(IllegalArgumentException.class, "Entity can not have 2 of the same components");
        }

    }

    public T getComponent(Entity entity)
    {
        T component = components.get(entity);
        if(component == null)
        {
            ExceptionHandler.raise(IllegalArgumentException.class, "Entity does not contain component");
        }
        return component;
    }







}
