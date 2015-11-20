package com.testing.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testing.game.IntelliJGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bucket, innit";
		config.width = 512+256;
		config.height = 512;
        config.resizable = false;
		new LwjglApplication(new IntelliJGame(), config);
	}
}
