package com.conductorio.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.conductorio.game.Conductorio;
import com.conductorio.game.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Conductorio";
		config.height = Constants.SCREEN_HEIGHT;
		config.width = Constants.SCREEN_WIDTH;
		new LwjglApplication(new Conductorio(), config);
	}
}
