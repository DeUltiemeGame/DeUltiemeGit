package com.cursor.game;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.framework.Graphics.ImageFormat;

public class SplashScreen extends Screen {
	public SplashScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash = g.newImage("splash.png", ImageFormat.RGB565);

		game.setScreen(new LoadingScreen(game));

	}

	@Override
	public void paint(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}