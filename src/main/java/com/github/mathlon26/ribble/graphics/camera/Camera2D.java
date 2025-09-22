package com.github.mathlon26.ribble.graphics.camera;

import com.github.mathlon26.ribble.core.Engine;
import com.github.mathlon26.ribble.core.Ribble;
import com.github.mathlon26.ribble.math.Transform3D;
import com.github.mathlon26.ribble.math.Vector2D;
import lombok.Getter;
import lombok.Setter;

public class Camera2D {
    @Getter @Setter
    private float zoom;

    @Getter @Setter
    private float nearClip;

    @Getter @Setter
    private float farClip;

    @Getter @Setter
    private Vector2D viewportSize;


    public Camera2D(float zoom, int viewportWidth, int viewportHeight) {
        this.zoom = zoom;
        this.nearClip = Ribble.CAM_NEAR_CLIP_DEFAULT;
        this.farClip = Ribble.CAM_FAR_CLIP_DEFAULT;
        this.viewportSize = new Vector2D(viewportWidth, viewportHeight);
    }

    public Camera2D(float zoom, Vector2D viewportSize) {
        this.zoom = zoom;
        this.nearClip = Ribble.CAM_NEAR_CLIP_DEFAULT;
        this.farClip = Ribble.CAM_FAR_CLIP_DEFAULT;
        this.viewportSize = viewportSize;
    }

    public Camera2D() {
        this(1, Engine.getInstance().getWindow().getSize());
    }


}
