package com.github.mathlon26.ribble_deprecated.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceUtils {

    public static File getResourceFile(String path) {
        try {
            URL url = ResourceUtils.class.getClassLoader().getResource(path);
            return (url != null) ? new File(url.toURI()) : null;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to resolve resource path: " + path, e);
        }
    }

    public static String stripExtension(File file) {
        String name = file.getName();
        int idx = name.lastIndexOf('.');
        return (idx > 0) ? name.substring(0, idx) : name;
    }
}
