package com.conductorio.game.Logic;

import com.badlogic.gdx.graphics.Texture;

public class Character {
    private String name;
    private Texture face;

    public Character(String name, Texture face) {
        this.name = name;
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getFace() {
        return face;
    }
}
