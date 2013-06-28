package com.cursor.game;

import android.graphics.Color;
import android.graphics.Paint;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.framework.Input.TouchEvent;

public class Credits extends Screen {
	Paint paint;

	public Credits(Game game) {
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
		g.drawString("Made By:.", 400, 150, paint);
		g.drawString("Erwin Kraan.", 400, 180, paint);
		g.drawString("Roy Koeleman.", 400, 210, paint);
		g.drawString("Moreno van der Kruit.", 400, 240, paint);
		g.drawString("Kevin Hansink.", 400, 270, paint);
		g.drawString("Berend Scheffers.", 400, 300, paint);
		g.drawString("Barry de Wit.", 400, 330, paint);

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
}
