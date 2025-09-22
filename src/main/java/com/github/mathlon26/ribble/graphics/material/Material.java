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
    private long shaderID;

    @Setter
    @Getter
    private Texture2D texture;
    private Color colorTint;
    private int renderLayer;

    public Material(long shaderID, Texture2D texture) {
        this.shaderID = shaderID;
        this.texture = texture;
        this.colorTint = Color.White();
        this.renderLayer = 0;
    }

    public void bind() {
        if (texture != null) {
            texture.bind();
            // setUniform1i
        }
        // setUniform4f
    }

    public Color getColorTint() { return colorTint; }
    public int getRenderLayer() { return renderLayer; }

    public void setColorTint(Color colorTint) { this.colorTint = colorTint; }
    public void setRenderLayer(int renderLayer) { this.renderLayer = renderLayer; }
}
