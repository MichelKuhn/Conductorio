package com.conductorio.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.conductorio.game.Conductorio;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Conductorio";
		config.height = 600;
		config.width = 200;

		new LwjglApplication(new Conductorio(), config);
	}
}
