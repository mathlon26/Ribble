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
        EntityManager man = EntityManager.getInstance();

        Entity entity1 = man.createEntity();
        TransformComponent transform = new TransformComponent(new Transform());

        man.addComponentToEntity(transform, entity1);

        assertEquals(transform, man.getComponentFromEntity(TransformComponent.class, entity1));

    }
}
