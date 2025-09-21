package ecs.system;

import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.system.SystemManager;
import ecs.component.TestComponent;
import org.junit.jupiter.api.Test;

public class SystemManagerTest {
    @Test
    void TestAddSystem()
    {
        SystemManager man = new SystemManager();

        TestSystemBase sys = new TestSystemBase();

        man.addSystem(sys);

        // No idea how to test if success
    }

    @Test
    void TestUpdateSystem()
    {
        // TODO test for value change

        EntityManager man = EntityManager.getInstance();

        Entity entity1 = man.createEntity();
        TestComponent comp = new TestComponent(0);

        man.addComponentToEntity(comp, entity1);
        man.addSystem(new TestSystemBase());

        man.update();
    }

}
