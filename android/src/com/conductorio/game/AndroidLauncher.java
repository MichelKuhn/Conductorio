package com.conductorio.game;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		DisplayMetrics dm = getResources().getDisplayMetrics();

		Conductorio game = new Conductorio();
		game.setHeight(dm.heightPixels);
		game.setWidth(dm.widthPixels);
		initialize(game, config);
	}
}
