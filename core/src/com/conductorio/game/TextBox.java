package com.conductorio.game;

public class TextBox {
    private int x;
    private int y;
    private String text;
    private boolean visible;

    TextBox(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.visible = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
