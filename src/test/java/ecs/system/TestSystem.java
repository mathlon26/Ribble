package ecs.system;

import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.io.output.sys.Logger;

public class TestSystem extends SystemBase {
    @Override
    public void start() {
        // TODO set TestComponent values
    }

    @Override
    public void update(double deltatime) {
//        for(TestComponent comp : EntityManager.getInstance().getComponentsOfType(TestComponent.class))
//        {
//            comp.setTestvalue(comp.getTestValue()+1);
//            Logger.getInstance().info(""+comp.getTestValue());
//        }

        Logger.getInstance().info("Deltatime: " + deltatime);
    }

    @Override
    public void destroy() {
        // TODO set TestComponent values
    }
}
