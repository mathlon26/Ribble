package com.github.mathlon26.ribble.ecs.component;

import com.github.mathlon26.ribble.math.Vector2D;
import lombok.Getter;
import lombok.Setter;

public class UIComponent extends Component {
    @Getter
    @Setter
    private Vector2D size;

    public UIComponent(Vector2D size) {
        this.size = size;
    }
}
