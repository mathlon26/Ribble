package com.github.mathlon26.ribble.ecs.component;

import com.github.mathlon26.ribble.math.Transform;
import com.github.mathlon26.ribble.math.Vector3D;
import lombok.Getter;
import lombok.Setter;

public class TransformComponent extends Component {
    @Getter
    @Setter
    private Transform transform;

    public TransformComponent(Transform transform) {
        this.transform = transform;
    }
}
