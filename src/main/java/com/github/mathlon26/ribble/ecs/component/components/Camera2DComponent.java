package com.github.mathlon26.ribble.ecs.component.components;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.graphics.camera.Camera2D;
import com.github.mathlon26.ribble.math.Matrix2D;
import lombok.Getter;
import lombok.Setter;

public class Camera2DComponent extends Component {
    @Getter
    @Setter
    private Camera2D camera2D;

    public Camera2DComponent(Camera2D camera) {
        this.camera2D = camera;
    }
}
