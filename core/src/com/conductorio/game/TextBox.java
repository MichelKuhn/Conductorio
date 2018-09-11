package com.conductorio.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextBox {
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private boolean visible;

    public TextBox(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.text = text;
        this.visible = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
