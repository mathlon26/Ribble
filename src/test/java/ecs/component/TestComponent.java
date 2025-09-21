package ecs.component;

import com.github.mathlon26.ribble.ecs.component.Component;




public class TestComponent extends Component {


    private int m_testValue;

    public void setTestvalue(int value)
    {
        m_testValue = value;
    }
    public int getTestValue()
    {
        return m_testValue;
    }


    public TestComponent(int testValue)
    {
        m_testValue = testValue;
    }

}
