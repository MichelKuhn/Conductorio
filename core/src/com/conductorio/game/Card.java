package com.conductorio.game;

public class Card {
    private Character character;
    private String text;
    private Choice left;
    private Choice right;

    Card(Character character, String text, Choice left, Choice right) {
        this.character = character;
        this.text = text;
        this.left = left;
        this.right = right;
    }

    public Character getCharacter() {
        return character;
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
