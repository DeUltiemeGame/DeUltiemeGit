package com.cursor.game;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.framework.Input.TouchEvent;

public class Options extends Screen {
	Paint paint, paint2, paint3, paint4;

	private static boolean ThemeSound = true;
	private static boolean GameSound = true;
	Naam naam;

	public Options(Game game) {
		super(game);
		// Maakt de tekst objecten aan
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);

		paint2 = new Paint();
		paint2.setTextSize(20);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.RED);
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
				// Themesound On/off
				if (inBounds(event, 290, 165, 230, 50)) {
					if (ThemeSound == true) {
						Assets.gameover.stop();
						ThemeSound = false;
					} else if (ThemeSound == false) {
						Assets.gameover.play();
						ThemeSound = true;
					}
				}
				if (inBounds(event, 290, 265, 230, 50)) {
					if (GameSound == true) {
						SoundOptie.setVolume(0);
						GameSound = false;
					} else if (GameSound == false) {
						SoundOptie.setVolume(100);
						GameSound = true;
					}
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
		g.drawString("Touch to Change", 400, 150, paint2);
		if (ThemeSound == true) {
			g.drawString("Themesound: On", 400, 200, paint);
		} else {
			g.drawString("Themesound: Off", 400, 200, paint);
		}
		if (GameSound == true) {
			g.drawString("GameSound: On", 400, 300, paint);
		} else {
			g.drawString("GameSound: Off", 400, 300, paint);
		}
	}

	@Override
	public void pause() {
		Assets.gameover.stop();
	}

	@Override
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
		game.setScreen(new MainMenuScreen(game));
	}

	public static boolean isThemeSound() {
		return ThemeSound;
	}

	public static void setThemeSound(boolean themeSound) {
		ThemeSound = themeSound;
	}
}
