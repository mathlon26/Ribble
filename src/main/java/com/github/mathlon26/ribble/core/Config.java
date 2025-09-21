package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.SettingsManager;
import com.github.mathlon26.ribble.math.physics.Color;

public class Config {

    private static final SettingsManager settings = SettingsManager.getInstance();

    static {
        settings.createDefaultSetting("settingsPath", "settings.json");
        settings.createDefaultSetting("logPath", "run.log");
        settings.createDefaultSetting("windowSize", Ribble.WINDOW_SIZE_HD);
        settings.createDefaultSetting("windowTitle", "Ribble Game Engine");
        settings.createDefaultSetting("windowBgColor", Color.Black());
        settings.createDefaultSetting("targetFPS", Ribble.FPS_60);
        settings.createDefaultSetting("vSync", true);
        settings.createDefaultSetting("masterVolume", Ribble.AUDIO_SET_MAX);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return settings.get(key, clazz);
    }

    public static void set(String key, Object value) {
        settings.set(key, value);
    }

    public static void loadPreferences() {
        settings.loadPreferencesToSettings();
    }

    public static void saveAsPreferences() {
        settings.saveSettingsAsPreferences();
    }

    public static void resetToDefaults() {
        settings.resetToDefaults();
    }

    public static void init() {
        settings.loadSettings();
    }

    private Config() {}
}
