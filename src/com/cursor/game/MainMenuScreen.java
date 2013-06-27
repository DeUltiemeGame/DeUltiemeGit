package com.cursor.game;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.framework.Input.TouchEvent;
import com.cursor.framework.implementation.AndroidGame;

public class MainMenuScreen extends Screen {
	Paint paint, paint2, paint3, paint4;
	private final int kleur = Color.argb(255, 0, 0, 0);

	public MainMenuScreen(Game game) {
		super(game);

		// Maakt de tekst objecten aan
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
		
		paint2 = new Paint();
		paint2.setTextSize(30);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
		paint2.setFakeBoldText(true);
	}

	@Override
	public void update(float deltaTime) {
		
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				// playButton
				if (inBounds(event, 650, 70, 100, 50)) {
					game.setScreen(new GameScreen(game));
				}
				// Options
				if (inBounds(event, 630, 170, 130, 50)) {
					game.setScreen(new Options(game));
				}
				// Highscore
				if (inBounds(event, 620, 270, 152, 50)) {
					game.setScreen(new Highscore(game));
				}
				// credits
				if (inBounds(event, 50, 420, 130, 50)) {
					game.setScreen(new Credits(game));
				}
				// Donate
				if (inBounds(event, 250, 420, 130, 50)) {
					game.setScreen(new Donate(game));
				}

			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
		g.drawString("Welkom, "+Naam.getNaam(), 10, 30, paint2);
		g.drawString("Play", 700, 100, paint);
		g.drawString("Options", 700, 200, paint);
		g.drawString("Highscore", 700, 300, paint);
		g.drawString("Credits", 100, 450, paint);
		g.drawString("Donate", 300, 450, paint);
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
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
