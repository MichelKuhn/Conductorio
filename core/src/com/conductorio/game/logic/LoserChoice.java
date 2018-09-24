package com.conductorio.game.logic;

public class LoserChoice extends Choice {
    LoserChoice() {
        super("Es ist vorbei", 0, 0, 0, 0);
    }

    @Override
    public boolean pick() {
        return false;
    }
}
