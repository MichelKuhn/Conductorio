package com.conductorio.game.Widgets;

import com.badlogic.gdx.graphics.Texture;

public class FieldBackground extends Widget{
    private Texture texture;

    public FieldBackground(int x, int y, Texture texture) {
        super(x, y);
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
