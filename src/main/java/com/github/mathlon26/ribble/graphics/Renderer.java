package com.github.mathlon26.ribble.graphics;

import com.github.mathlon26.ribble.ecs.component.components.Camera2DComponent;
import com.github.mathlon26.ribble.ecs.component.components.SpriteComponent;
import com.github.mathlon26.ribble.ecs.component.components.Transform2DComponent;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.graphics.material.Material;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.EntityManager;
import com.github.mathlon26.ribble.math.Matrix2D;
import com.github.mathlon26.ribble.math.Transform2D;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Renderer {

    private Entity cameraEntity = null;
    private Camera2DComponent cameraComponent = null;
    private Transform2DComponent cameraTransformComponent = null;

    private EntityManager entityManager = EntityManager.getInstance();
    public Renderer() {}

    public void render() {

        setupCamera();

        Collection<Entity> sprites = getSprites();
        for (Entity renderable : sprites) {
            Transform2DComponent transform = entityManager.getComponentFromEntity(Transform2DComponent.class, renderable);
            SpriteComponent sprite = entityManager.getComponentFromEntity(SpriteComponent.class, renderable);

            Material material = sprite.getMaterial();
            material.bind();

            Matrix2D proj = Matrix2D.orthographic(0, cameraComponent.getCamera2D().getViewportSize().getX(), 0, cameraComponent.getCamera2D().getViewportSize().getY());

            Matrix2D viewProjection = buildViewMatrix(cameraTransformComponent);
            Matrix2D model = buildModelMatrix(transform);
            Matrix2D mvp = Matrix2D.multiply(viewProjection, model);
            material.getShader().setUniformMatrix("u_MVP", mvp);

            // meshRenderer.drawQuad(sprite.getSourceRect());
            material.unbind();
        }


    }

    private Collection<Entity> getSprites() {
        Set<Entity> sprites = entityManager.getEntitiesWithComponentSet(
                SpriteComponent.class,
                Transform2DComponent.class
        );

        return sprites;
    }

    private void setupCamera() {
        Collection<Entity> cameras = entityManager.getEntitiesWithComponentSet(
                Camera2DComponent.class,
                Transform2DComponent.class
        );
        List<Entity> cameraEntities = cameras.stream().toList();
        for (Entity entity : cameraEntities) {
            if (entity.isActive()
                    && entityManager.getComponentFromEntity(Camera2DComponent.class, entity).isActive()
                    && entityManager.getComponentFromEntity(Transform2DComponent.class, entity).isActive()
            ) {
                cameraEntity = entity;
                cameraComponent = entityManager.getComponentFromEntity(Camera2DComponent.class, entity);
                cameraTransformComponent = entityManager.getComponentFromEntity(Transform2DComponent.class, entity);
                break;
            }
        }
    }

    private Matrix2D buildModelMatrix(Transform2DComponent transform) {
        Matrix2D translation = Matrix2D.translation(transform.getTransform2D().getPosition().getX(), transform.getTransform2D().getPosition().getY());
        Matrix2D rotation = Matrix2D.rotation(transform.getTransform2D().getRotation());
        Matrix2D scale = Matrix2D.scale(transform.getTransform2D().getScale().getX(), transform.getTransform2D().getScale().getY());

        // Model = T * R * S
        return Matrix2D.multiply(translation, Matrix2D.multiply(rotation, scale));
    }

    private Matrix2D buildViewMatrix(Transform2DComponent camTransform) {
        Matrix2D translation = Matrix2D.translation(-camTransform.getTransform2D().getPosition().getX(), -camTransform.getTransform2D().getPosition().getY());
        Matrix2D rotation = Matrix2D.rotation(-camTransform.getTransform2D().getRotation());
        Matrix2D scale = Matrix2D.scale(
                1.0 / camTransform.getTransform2D().getScale().getX(),
                1.0 / camTransform.getTransform2D().getScale().getY()
        );

        // View = S * R * T (inverse order of Model)
        return Matrix2D.multiply(scale, Matrix2D.multiply(rotation, translation));
    }
}
