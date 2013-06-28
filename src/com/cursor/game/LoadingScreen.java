package com.cursor.game;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Graphics.ImageFormat;
import com.cursor.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		// laad de image in
		Graphics g = game.getGraphics();
		Assets.splash = g.newImage("splash.png", ImageFormat.RGB565);
		Assets.opties = g.newImage("opties.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.speler = g.newImage("cursor.png", ImageFormat.RGB565);
		Assets.vijand = g.newImage("heliboy.png", ImageFormat.RGB565);
		Assets.bullet = g.newImage("bullet.png", ImageFormat.RGB565);
		Assets.premenu = g.newImage("premenu.png", ImageFormat.RGB565);
		// Laad sounds in
		Assets.jumpsound = game.getAudio().createSound("jump.wav");
		Assets.bulletsound = game.getAudio().createSound("bullet.wav");
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 400, 240);
	}

	@Override
	public void pause() {
		Assets.gameover.stop();
	}

	public void resume() {
		if (Options.isThemeSound() == true) {
			Assets.gameover.play();
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}
