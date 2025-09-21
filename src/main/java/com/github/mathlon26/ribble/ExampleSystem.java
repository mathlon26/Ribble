package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.core.Engine;
import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.SceneManager;
import com.github.mathlon26.ribble.math.physics.Color;

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

                            Engine.getInstance().getWindow().setBackgroundColor(
                                    new Color(
                                            1 - ((float) e.count / 100),
                                            ((float) e.count / 100) * ((float) e.count / 100),
                                            (float) e.count / 100)
                            );

                            if (e.count >= 100) {
                                SceneManager.getInstance().loadScene(new ExampleScene2());
                            }
                        }
                );
    }

    @Override
    public void destroy() {

    }
}
