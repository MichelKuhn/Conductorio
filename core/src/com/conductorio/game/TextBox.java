package com.conductorio.game;

public class TextBox extends Widget {
    private String text;
    private boolean visible;

    TextBox(int x, int y, String text) {
        super(x, y);
        this.text = text;
        this.visible = false;
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
