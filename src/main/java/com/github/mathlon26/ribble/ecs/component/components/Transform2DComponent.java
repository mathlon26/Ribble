package com.github.mathlon26.ribble.ecs.component.components;

import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.math.Transform2D;
import lombok.Getter;
import lombok.Setter;

public class Transform2DComponent extends Component {
    @Getter
    @Setter
    private Transform2D transform2D;

    public Transform2DComponent(Transform2D transform) {
        this.transform2D = transform;
    }
}
