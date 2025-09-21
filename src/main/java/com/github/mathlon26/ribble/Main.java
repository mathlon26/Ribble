package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.core.RibbleGame;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;
import com.github.mathlon26.ribble.io.output.sys.Logger;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            new ExampleGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
