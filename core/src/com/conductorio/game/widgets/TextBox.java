package com.conductorio.game.widgets;

public class TextBox extends Widget {
    private String text;

    public TextBox(int x, int y, String text) {
        super(x, y);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
