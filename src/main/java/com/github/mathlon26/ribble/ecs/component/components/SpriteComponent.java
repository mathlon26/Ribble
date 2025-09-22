package com.github.mathlon26.ribble.ecs.component.components;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.graphics.material.Material;
import com.github.mathlon26.ribble.math.geometry.Rectangle;

public class SpriteComponent extends Component {
    private Material material;
    private Rectangle sourceRect; // region of the texture (useful for sprite sheets)

    public SpriteComponent(Material material) {
        this.material = material;
        if (material.getTexture() != null) {
            this.sourceRect = new Rectangle(
                    0, 0,
                    material.getTexture().getWidth(),
                    material.getTexture().getHeight()
            );
        }
    }

    public Material getMaterial() { return material; }
    public Rectangle getSourceRect() { return sourceRect; }

    public void setMaterial(Material material) { this.material = material; }
    public void setSourceRect(Rectangle rect) { this.sourceRect = rect; }
}
