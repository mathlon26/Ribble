package ecs.component;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.component.ComponentPool;
import com.github.mathlon26.ribble.ecs.component.RenderComponent;
import com.github.mathlon26.ribble.ecs.component.components.Transform3DComponent;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.math.Transform3D;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        ComponentPool<Transform3DComponent> transformPool = new ComponentPool<>();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);
        Transform3DComponent transform1 = new Transform3DComponent(new Transform3D());
        Transform3DComponent transform2 = new Transform3DComponent(new Transform3D());

        transformPool.addComponent(entity1, transform1);
        transformPool.addComponent(entity2, transform2);

        assertEquals(transform1, transformPool.getComponent(entity1));
        assertNotEquals(null, transformPool.getComponent(entity1));
        assertNotSame(transform1, transformPool.getComponent(entity2));
    }

    @Test
    void testGetComponentsStandalone()
    {
        ComponentPool<Transform3DComponent> pool = new ComponentPool<>();

        Entity entity = new Entity(0);
        Transform3DComponent trans = new Transform3DComponent(new Transform3D());

        pool.addComponent(entity, trans);

        List<Transform3DComponent> expected = List.of(trans);

        Collection<Transform3DComponent> components = pool.getComponents();

        assertTrue(components.containsAll(expected));
        assertTrue(expected.containsAll(components));
        assertNotEquals(null, components);
    }

    @Test
    void testGetComponents()
    {
        EntityManager man = EntityManager.getInstance();
        Entity entity = man.createEntity();
        Transform3DComponent trans = new Transform3DComponent(new Transform3D());
        man.addComponentToEntity(trans, entity);

        List<Transform3DComponent> expected = List.of(trans);
        Collection<Transform3DComponent> components = man.getComponentsOfType(Transform3DComponent.class);

        assertTrue(components.containsAll(expected));

        assertTrue(expected.containsAll(components));

        assertNotEquals(null, components);

    }

}
