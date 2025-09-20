package com.github.mathlon26.ribble.ecs.component;

import lombok.Getter;
import lombok.Setter;

public class RenderComponent extends Component{
    @Getter
    @Setter
    private long meshID;

    @Getter
    @Setter
    private long textureID;

    public RenderComponent (long meshID, long textureID) {
        this.meshID = meshID;
        this.textureID = textureID;
    }
}
