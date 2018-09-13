package com.conductorio.game.Widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ValueCounter extends Widget {
    private int value;
    private int size;
    private Texture texture, textureWhite;

    private Texture scaleImg(String path) {
        Pixmap normalPixmap = new Pixmap(Gdx.files.internal(path));
        Pixmap pixmapNormalScaled = new Pixmap(size, size, normalPixmap.getFormat());
        pixmapNormalScaled.drawPixmap(normalPixmap,
                0, 0, normalPixmap.getWidth(), normalPixmap.getHeight(),
                0, 0, pixmapNormalScaled.getWidth(), pixmapNormalScaled.getHeight()
        );

        return new Texture(pixmapNormalScaled);
    }

    public ValueCounter(int x, int y, String pathNormal, String pathWhite, int size) {
        super(x, y);
        this.value = 50;
        this.size = size;
        texture = scaleImg(pathNormal);
        textureWhite = scaleImg(pathWhite);
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
        return value * size / 100;
    }
}
