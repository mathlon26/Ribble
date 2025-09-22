package com.github.mathlon26.ribble.graphics.material;

import com.github.mathlon26.ribble.graphics.shader.Shader;
import com.github.mathlon26.ribble.graphics.texture.Texture2D;
import com.github.mathlon26.ribble.math.physics.Color;
import lombok.Getter;
import lombok.Setter;

public class Material {
    // getters and setters
    @Setter
    @Getter
    private Shader shader;

    @Setter
    @Getter
    private Texture2D texture;
    private Color colorTint;
    private int renderLayer;

    public Material(Shader shader, Texture2D texture) {
        this.shader = shader;
        this.texture = texture;
        this.colorTint = Color.White();
        this.renderLayer = 0;
    }

    public void bind() {
        shader.bind();
        if (texture != null) {
            texture.bind();
            shader.setUniform1i("u_Texture", texture.getSlot());
        }
        shader.setUniform4f("u_ColorTint", colorTint);
    }

    public void unbind() {
        if (texture != null) texture.unbind();
        shader.unbind();
    }

    public Color getColorTint() { return colorTint; }
    public int getRenderLayer() { return renderLayer; }

    public void setColorTint(Color colorTint) { this.colorTint = colorTint; }
    public void setRenderLayer(int renderLayer) { this.renderLayer = renderLayer; }
}
