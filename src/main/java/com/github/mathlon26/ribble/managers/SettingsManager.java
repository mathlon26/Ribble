package com.github.mathlon26.ribble.managers;

import com.github.mathlon26.ribble.core.Ribble;
import com.github.mathlon26.ribble.math.physics.Color;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {

    private static final String DEFAULT_FILE_PATH = "settings.json";

    // Singleton instance
    private static SettingsManager instance;

    private final Map<String, Object> defaultSettings = new HashMap<>();
    private final Map<String, Object> currentSettings = new HashMap<>();

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    private SettingsManager() {}

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    // Add a single default setting
    public void createDefaultSetting(String key, Object value) {
        defaultSettings.put(key, value);
        currentSettings.putIfAbsent(key, value);
    }

    // Get a setting
    public <T> T get(String key, Class<T> clazz) {
        Object value = currentSettings.get(key);
        if (value != null) {
            return gson.fromJson(gson.toJson(value), clazz);
        }
        return null;
    }

    // Set a setting
    public void set(String key, Object value) {
        currentSettings.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public void loadSettings() {
        File file = new File(DEFAULT_FILE_PATH);
        currentSettings.clear();

        Map<String, Object> loaded = null;
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
                loaded = gson.fromJson(reader, mapType);
            } catch (IOException e) {
                throw new RuntimeException("SettingsManager::loadSettings -> IOException -> RuntimeException " + e);
            }
        }

        boolean updated = false;

        if (loaded != null) {
            currentSettings.putAll(loaded);
        }

        for (Map.Entry<String, Object> e : defaultSettings.entrySet()) {
            if (!currentSettings.containsKey(e.getKey())) {
                currentSettings.put(e.getKey(), e.getValue());
                updated = true;
            }
        }

        if (!file.exists() || updated) {
            saveSettings();
        }
    }



    // Save settings to JSON file
    public void saveSettings() {
        try (FileWriter writer = new FileWriter(DEFAULT_FILE_PATH)) {
            gson.toJson(currentSettings, writer);
        } catch (IOException e) {
            throw new RuntimeException("SettingsManager::saveSettings -> IOException -> RuntimeException " + e);
        }
    }

    // Load preferences into current settings (alias)
    public void loadPreferencesToSettings() {
        loadSettings();
    }

    // Save current settings as preferences (alias)
    public void saveSettingsAsPreferences() {
        saveSettings();
    }

    // Reset current settings to defaults
    public void resetToDefaults() {
        currentSettings.clear();
        currentSettings.putAll(defaultSettings);
        saveSettings();
    }
}
