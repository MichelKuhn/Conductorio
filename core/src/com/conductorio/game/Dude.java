package com.conductorio.game;

import com.badlogic.gdx.graphics.Texture;

public class Dude extends Widget {
    private Texture face;

    Dude(int x, int y, Texture face) {
        super(x, y);
        this.face = face;
    }

    public Texture getFace() {
        return face;
    }

    public void setFace(Texture face) {
        this.face = face;
    }
}
