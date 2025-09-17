package com.github.mathlon26.ribble.core.graphics.rendering;

public class Indices {
    private int[] data;

    public Indices(int... values) {
        this.data = values;
    }

    public int[] toIntArray() {
        return this.data;
    }

    public void setData(int... values) {
        this.data = values;
    }

    public static Indices from(int... values) {
        return new Indices(values);
    }

    public int getLength() {
        return this.data.length;
    }
}
