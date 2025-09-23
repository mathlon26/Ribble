package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.core.Engine;
import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.io.input.devices.KeyBoardKey;
import com.github.mathlon26.ribble.io.input.listeners.KeyboardListener;
import com.github.mathlon26.ribble.math.physics.Color;

import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class ExampleBGSystem extends SystemBase {
    private static Random rd = new Random();
    private Stack<Color> m_stack;

    @Override
    public void start() {
        m_stack = new Stack<>();
        m_stack.push(Engine.getInstance().getWindow().getBackgroundColor());
    }

    @Override
    public void update(double deltatime) {

        if (KeyboardListener.isKeyPressed(KeyBoardKey.SPACE)) {
            Color newColor = new Color(
                    rd.nextFloat(),
                    rd.nextFloat(),
                    rd.nextFloat()
            );

            m_stack.push(newColor);

            Engine.getInstance().getWindow().setBackgroundColor(
                newColor
            );
        }

        if (KeyboardListener.isKeyPressed(KeyBoardKey.BACKSPACE)) {
            if (m_stack.size() <= 1) return;  // keep at least the initial color
            m_stack.pop();  // remove current
            Color newColor = m_stack.peek();  // get previous
            Engine.getInstance().getWindow().setBackgroundColor(newColor);
        }
    }

    @Override
    public void destroy() {

    }
}
