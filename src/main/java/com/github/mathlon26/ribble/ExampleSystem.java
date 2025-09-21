package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.io.output.sys.Logger;

public class ExampleSystem extends SystemBase {

    @Override
    public void start() {

    }

    @Override
    public void update(double deltatime) {

        getComponentsOfType(ExampleComponent.class)
                .forEach(
                        (ExampleComponent e) -> {
                            e.count += 1;
                            Logger.getInstance().info("ExampleComponent.count: "+e.count);
                        }
                );
    }

    @Override
    public void destroy() {

    }
}
