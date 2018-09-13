package com.conductorio.game.Widgets;

import com.badlogic.gdx.graphics.Texture;
import com.conductorio.game.Constants;

public class ValueCounter extends Widget {
    private int value;
    private Texture texture, textureWhite;

    public ValueCounter(int x, int y, Texture texture, Texture textureWhite) {
        super(x, y);
        this.value = 50;
        this.texture = texture;
        this.textureWhite = textureWhite;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getTextureWhite() {
        return textureWhite;
    }

    public int getValueInPx() {
        return value * Constants.VALUE_COUNTER_SIZE / 100;
    }
}
