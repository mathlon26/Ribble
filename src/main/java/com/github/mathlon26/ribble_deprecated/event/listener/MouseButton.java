package com.github.mathlon26.ribble_deprecated.event.listener;

public enum MouseButton {
    LEFT(0),    // GLFW_MOUSE_BUTTON_LEFT
    RIGHT(1),   // GLFW_MOUSE_BUTTON_RIGHT
    MIDDLE(2),  // GLFW_MOUSE_BUTTON_MIDDLE
    BTN_1(0),
    BTN_2(1),
    BTN_3(2),
    BTN_4(3),
    BTN_5(4),
    BTN_6(5),
    BTN_7(6),
    BTN_8(7),
    LAST(7),
    INVALID(-1);

    public static final int MAX_BUTTONS = 8;
    private final int code;

    MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButton fromCode(int code) {
        for (MouseButton button : values()) {
            if (button.code == code) return button;
        }
        throw new IllegalArgumentException("Unknown mouse button code: " + code);
    }
}
