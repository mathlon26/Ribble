package ecs.component;

import com.github.mathlon26.ribble.ecs.component.ComponentPool;
import com.github.mathlon26.ribble.ecs.component.RenderComponent;
import com.github.mathlon26.ribble.ecs.component.TransformComponent;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.math.Transform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ComponentPoolTest {

    @Test
    void testConstructor()
    {
        ComponentPool<RenderComponent> renderPool = new ComponentPool<>();
        // No idea how to actually test
    }

    @Test
    void testAddComponent()
    {
        ComponentPool<TransformComponent> transformPool = new ComponentPool<>();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);
        TransformComponent transform1 = new TransformComponent(new Transform());
        TransformComponent transform2 = new TransformComponent(new Transform());

        transformPool.addComponent(entity1, transform1);
        transformPool.addComponent(entity2, transform2);

        assertEquals(transform1, transformPool.getComponent(entity1));
        assertNotEquals(null, transformPool.getComponent(entity1));
        assertNotEquals(transform1, transformPool.getComponent(entity2));
    }



}
