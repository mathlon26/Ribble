package ecs;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.component.TransformComponent;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.math.Transform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntityManagerTest {

    @Test
    void TestComponents()
    {
        // Create a local entitymanager
        EntityManager man = new EntityManager();

        Entity entity1 = man.createEntityInstance();
        TransformComponent transform = new TransformComponent(new Transform());

        man.addComponentTo(entity1, transform);

        assertEquals(transform, man.getComponentFor(entity1, TransformComponent.class));

    }
}
