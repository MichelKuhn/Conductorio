package com.conductorio.game;

public final class Constants {
    public static final int SCREEN_HEIGHT = 568;
    public static final int SCREEN_WIDTH = 320;

    public static final int BORDER = SCREEN_WIDTH / 20;
    public static final int FONT_BORDER = BORDER / 2;

    public static final int CHOICE_BOX_SIZE = 96;

    public static final int FLOOR_TO_DUDE = 96;
    public static final int DUDE_BOX_SIZE = 256;
    public static final int DUDE_TO_TEXT = 32;

    public static final int TEXTFIELD_HEIGHT = 96;
    public static final int TEXT_TO_STATS = 32;

    public static final int STATS_HEIGHT = 32;
    public static final int STATS_ROOM = 56;
    public static final int STATS_BORDER = 8;

    private Constants(){
        throw new AssertionError();
    }
}
