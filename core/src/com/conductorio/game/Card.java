package com.conductorio.game;

public class Card {
    private String text;
    private Choice left;
    private Choice right;

    public Card(String text, Choice left, Choice right) {
        this.text = text;
        this.left = left;
        this.right = right;
    }

    public String getText() {
        return text;
    }

    public Choice getLeft() {
        return left;
    }

    public Choice getRight() {
        return right;
    }
}
