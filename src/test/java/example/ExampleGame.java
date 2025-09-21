package example;

import com.github.mathlon26.ribble.core.RibbleGame;
import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import ecs.component.TestComponent;
import ecs.system.TestSystem;

public class ExampleGame extends RibbleGame {
    public ExampleGame() {
        super();

        Entity entity = EntityManager.getInstance().createEntity();

        TestComponent comp = new TestComponent(0);

        EntityManager.getInstance().addComponentToEntity(comp, entity);

        TestSystem sys = new TestSystem();
        EntityManager.getInstance().addSystem(sys);

        start();
    }
}
