package com.github.mathlon26.ribble.graphics.shader;

import com.github.mathlon26.ribble.assets.AssetLoader;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;
import com.github.mathlon26.ribble.math.Matrix2D;
import com.github.mathlon26.ribble.math.physics.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class Shader {

    public int shaderID;

    /**
     *
     * @param assetName the name of the shaderAsset (must be loaded with AssetLoader first)
     */
    public Shader(String assetName){
        shaderID = AssetLoader.getInstance().getShader(assetName);
    }


    public void bind() {
        if(shaderID != 0)
        {
            glUseProgram(shaderID);
        }else {
            ExceptionHandler.raise(NullPointerException.class, "Tried to use uninitialised shader!");
        }
    }

    public void unbind() {
        // set program to 0 so no shaders are used accidentally
        glUseProgram(0);
    }


}
