package com.cursor.game;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.framework.Input.TouchEvent;

public class Donate extends Screen {
	Paint paint, paint2, paint3, paint4;
	private final int kleur = Color.argb(255, 0, 0, 0);
	
	public Donate(Game game) {
		super(game);

		// Maakt de tekst objecten aan
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				//playButton
				if (inBounds(event, 650, 70, 100, 50)) {
					//game.setScreen(new GameScreen(game));
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
		game.setScreen(new MainMenuScreen(game));
	}
}
