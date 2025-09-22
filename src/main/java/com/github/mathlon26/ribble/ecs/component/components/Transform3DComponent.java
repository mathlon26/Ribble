package com.github.mathlon26.ribble.ecs.component.components;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.math.Transform3D;
import lombok.Getter;
import lombok.Setter;

public class Transform3DComponent extends Component {
    @Getter
    @Setter
    private Transform3D transform;

    public Transform3DComponent(Transform3D transform) {
        this.transform = transform;
    }
}
