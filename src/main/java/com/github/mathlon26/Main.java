package com.github.mathlon26;

import com.github.mathlon26.ribble.GameApplication;

public class Main {
    public static void main(String[] args) {
        try {
            new GameApplication().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}