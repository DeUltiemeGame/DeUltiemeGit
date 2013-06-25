package com.cursor.game;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import android.graphics.Color;
import android.graphics.Paint;
import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Screen;
import com.cursor.game.JSONParser;

public class Highscore extends Screen {
	Paint paint, paint2, paint3, paint4;
	private final int kleur = Color.argb(255, 0, 0, 0);
	private String namen[] = new String[100];
	private int scores[] = new int[100];
	private int y_start = 100;
	private boolean done = false;
	private boolean draw = false;
	Graphics g = game.getGraphics();

	public Highscore(Game game) {
		super(game);

		// Maakt de tekst objecten aan
		paint = new Paint();
		paint.setTextSize(40);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
		
		paint2 = new Paint();
		paint2.setTextSize(30);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
	}

	@Override
	public void paint(float deltaTime) {
	}

	@Override
	public void update(float deltaTime) {
		if (!done && !draw) {
			g.drawImage(Assets.opties, 0, 0);
			g.drawString("Highscores laden...", 400, 240, paint);
			getScores();
		} else if (done && !draw) {
			g.drawImage(Assets.opties, 0, 0);
			g.drawString("Naam",250,50,paint);
			g.drawString("Score",550,50,paint);
			y_start = y_start - 40;
			
			for(int i=0; i<10; i++) {
				y_start = y_start + 40;
				g.drawString(i+1+".",150,y_start,paint2);
				g.drawString(namen[i],250,y_start,paint2);
				g.drawString(Integer.toString(scores[i]),550,y_start,paint2);
			}
			
			
			draw = true;
		}
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

	private void getScores() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					String get_scores_url = "http://145.24.222.174/highscores/get_10_scores.php";
					JSONParser jParser = new JSONParser();
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					JSONObject json = jParser.makeHttpRequest(get_scores_url,
							"GET", params);

					if (json.getInt("success") == 1) {
						JSONArray data = json.getJSONArray("score");

						for (int i = 0; i < data.length(); i++) {
							JSONObject c = data.getJSONObject(i);
							namen[i] = c.getString("naam");
							scores[i] = c.getInt("score");
						}
						done = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}
}
