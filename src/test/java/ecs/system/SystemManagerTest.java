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

        TestSystem sys = new TestSystem();

        man.addSystem(sys);

        // No idea how to test if success
    }

    @Test
    void TestUpdateSystem()
    {
        // TODO test for value change

        EntityManager man = new EntityManager();

        Entity entity1 = man.createEntityInstance();
        TestComponent comp = new TestComponent(0);

        man.addComponentTo(entity1, comp);
        man.addSystemInst(new TestSystem());

        man.updateSystems();
    }

}
