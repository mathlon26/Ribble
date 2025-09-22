package ecs;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.component.components.Transform3DComponent;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.math.Transform3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntityManagerTest {

    @Test
    void TestComponents()
    {
        // Create a local entitymanager
        EntityManager man = EntityManager.getInstance();

        Entity entity1 = man.createEntity();
        Transform3DComponent transform = new Transform3DComponent(new Transform3D());

        man.addComponentToEntity(transform, entity1);

        assertEquals(transform, man.getComponentFromEntity(Transform3DComponent.class, entity1));

    }
}
