package com.github.mathlon26.ribble.core.editor;

import com.github.mathlon26.ribble.GameApplication;
import com.github.mathlon26.ribble.core.Window;
import com.github.mathlon26.ribble.core.graphics.Scene;
import com.github.mathlon26.ribble.core.graphics.SceneManager;
import com.github.mathlon26.ribble.event.listener.KeyBoardKey;
import com.github.mathlon26.ribble.event.listener.KeyListener;
import com.github.mathlon26.ribble.math.Vec4D;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private static final double CHANGE_SPEED = 1.0;
    private boolean changingScene = false;
    private double timeToChangeScene = 2.0;

    public LevelEditorScene() {
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void render() {

    }
}
