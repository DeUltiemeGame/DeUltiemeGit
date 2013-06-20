package com.cursor.game;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cursor.framework.Game;
import com.cursor.framework.Graphics;
import com.cursor.framework.Input.TouchEvent;
import com.cursor.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	Paint paint, paint2, paint3, highscore;
	// Boolean's om user Feedback te geven
	private boolean ingedruktUp;
	private boolean ingedruktDown;
	private boolean ingedruktLeft;
	private boolean ingedruktRight;
	//Int's om door te geven welke vinger is gebruikt
	private int vingerIdUp = 100;
	private int vingerIdDown = 100;
	private int vingerIdLeft = 100;
	private int vingerIdRight = 100;
	// exp
	private static int exp;
	// objecten
	private Enemy vijand;
	private Cursor speler;
	private Bullet kogel;

	// booleans
	private static boolean skill1 = false;
	private static boolean skill2 = false;
	private static boolean skill3 = false;
	private static boolean resetBulletPos = false;
	// resetBullion
	private static boolean resetSkill = false;

	// bepaald de kleur van de user feedback
	private final int kleur = Color.argb(55, 0, 0, 0);

	public GameScreen(Game game) {
		super(game);
		// Variable Setup
		exp = 0;
		// Initialize game objects here
		vijand = new Enemy();
		speler = new Cursor();
		kogel = new Bullet();
		// Maakt de tekst objecten aan
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		paint3 = new Paint();
		paint3.setTextSize(32);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.WHITE);

		highscore = new Paint();
		highscore.setTextSize(16);
		highscore.setTextAlign(Paint.Align.CENTER);
		highscore.setAntiAlias(true);
		highscore.setColor(Color.BLACK);
	}

	@Override
	public void update(float deltaTime) {
		List touchEvents = game.getInput().getTouchEvents();
		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);

	}

	private void updateGameOver(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
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

	private void updatePaused(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						state = GameState.Running;
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}

			}
		}
	}

	private void updateRunning(List touchEvents, float deltaTime) {
		speler.update();
		if (skill2 == false) {
			vijand.update();
		}
		spelerMovement(touchEvents);
		skillUitvoeren();
		checkCollionEnemy();
		exp++;
	}

	private void checkCollionEnemy() {
		if (Rect.intersects(vijand.getEnemy(), speler.getCursor()) == true) {
			state = GameState.GameOver;
		}

	}

	private void skillUitvoeren() {
		if (skill1 == true) {
			if (resetBulletPos == false) {
				vijand.kijkUpDown();
				updateBeginPosKogel();// geeft de waarde door aan bullet(was
										// niet
										// handig inverband met static)
				updateCursorPosKogel();// geeft de waarde door aan bullet(was
										// niet
										// handig inverband met static)
			}
			kogel.update();
			checkCollisionBullet();
		} else if (skill2 == true) {
			if (vijand.isResetPos() == false) {
				updateCursorPosvijand();
				vijand.setGetSkill(false);
			}
			vijand.jumpSkill();
		} else if (skill3 == true) {
			if (vijand.isResetPos() == false) {
				updateCursorPosvijand();
				vijand.setGetSkill(false);
			}
			vijand.skill3();

		} else if (resetSkill == true) {

			setNieuweSkill();
			resetSkill = false;
		}
	}

	private void spelerMovement(List touchEvents) {
		int len = touchEvents.size();
		Graphics g = game.getGraphics();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 200, 0, 400, 242)) {
					speler.setIngedruktUp(true);
					ingedruktUp = true;
					vingerIdUp = event.pointer;
				}
				if (inBounds(event, 200, 240, 400, 242)) {
					speler.setIngedruktDown(true);
					ingedruktDown = true;
					vingerIdDown = event.pointer;
				}
				if (inBounds(event, 0, 0, 200, 482)) {
					speler.setIngedruktLeft(true);
					ingedruktLeft = true;
					vingerIdLeft = event.pointer;
				}
				if (inBounds(event, 600, 0, 200, 482)) {
					speler.setIngedruktRight(true);
					ingedruktRight = true;
					vingerIdRight = event.pointer;
				}
			} 
			if (event.type == TouchEvent.TOUCH_UP) {
				if(event.pointer == vingerIdUp) {
					speler.setIngedruktUp(false);
					ingedruktUp = false;
					vingerIdUp = 100;
				} else if(event.pointer == vingerIdDown) {
					speler.setIngedruktDown(false);
					ingedruktDown = false;
					vingerIdDown = 100;  
				} else if(event.pointer == vingerIdLeft) {
					speler.setIngedruktLeft(false);
					ingedruktLeft = false;
					vingerIdLeft = 100;
				} else if(event.pointer == vingerIdRight) {
					speler.setIngedruktRight(false);
					ingedruktRight = false;
					vingerIdRight = 100;
				}
			}
		}
	}

	private void updateReady(List touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.background, 0, 0);
		g.drawImage(Assets.speler, speler.getPosX(), speler.getPosY());
		g.drawImage(Assets.vijand, vijand.getPosX(), vijand.getPosY());
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		tekenBullet();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void drawGameOverUI() {
		SchrijfExpinDb();
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);
		g.drawString("You earned " + exp + " exp", 400, 100, paint3);

	}

	private void SchrijfExpinDb() {

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Kleurt het scherm grijs zodat het te lezen valt.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		if (ingedruktUp == true) {
			g.drawRect(200, 0, 400, 242, kleur);
		}
		if (ingedruktDown == true) {
			g.drawRect(200, 240, 400, 242, kleur);
		}
		if (ingedruktLeft == true) {
			g.drawRect(0, 0, 200, 482, kleur);
		}
		if (ingedruktRight == true) {
			g.drawRect(600, 0, 200, 482, kleur);
		}
		g.drawString("Score: " + exp, 400, 20, highscore);
	}

	private void tekenBullet() {
		Graphics g = game.getGraphics();
		if (skill1 == true) {
			g.drawImage(Assets.bullet, kogel.getPosX(), kogel.getPosY());
		}
	}

	private void checkCollisionBullet() {
		if (Rect.intersects(kogel.getBullet(), speler.getCursor()) == true) {
			state = GameState.GameOver;
		}
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		// Kleurt het scherm grijs zodat het te lezen valt.
		g.drawARGB(155, 0, 0, 0);
		g.drawImage(Assets.premenu, 0, 0);
		g.drawString("Tap to start the game", 400, 240, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Running)
			state = GameState.Paused;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		pause();

	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		vijand = null;
		speler = null;
		kogel = null;
		// Static variable weer op normale waarde zetten zodat de game opniuew
		// gespeeld kan worden
		skill1 = false;
		resetBulletPos = false;
		skill2 = false;
		skill3 = false;

		exp = 0;
		// Call garbage collector to clean up memory.
		System.gc();

	}

	public static boolean getSkill1() {
		return skill1;
	}
	
	public static void setSkill1(boolean skill1) {
		GameScreen.skill1 = skill1;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public void updateCursorPosKogel() {
		kogel.setCursorPosX(speler.getPosX() + 16);// CursorX midden is PosX+44.
													// Om de
		// bullet in midden te krijgen van
		// de cursor -28 =-11
		kogel.setCursorPosY(speler.getPosY() - 35);// Cursor midden is posY+44.
													// Om de
		// bullet in midden te krijgen van
		// de cursor -9 = 35
	}

	public void updateBeginPosKogel() {
		if (vijand.isPosDown() == true) {
			kogel.setPosX(vijand.getPosX() + 46);// enemy 0 positie. Enemy = 92
													// breed om
			// midden te spawnen is +46 nodig
			kogel.setPosY(vijand.getPosY() - 23);// enemy 0 positie. Bullet = 18
													// hoog en
													// 5 boven de enemy = 23
													// omhoog.
		} else {
			kogel.setPosX(vijand.getPosX() + 46);// enemy 0 positie. Enemy = 92
			// breed om
			// midden te spawnen is +46 nodig
			kogel.setPosY(vijand.getPosY() + 23);// enemy 0 positie. Bullet = 18
			// hoog en
			// 5 boven de enemy = 23 omhoog.
		}
	}

	public void updateCursorPosvijand() {
		vijand.setCursorPosX(speler.getPosX() - 2);// CursorX midden is
													// PosX+44.
		// Om de
		// enemy in midden te krijgen van
		// de cursor -46 =-11
		vijand.setCursorPosY(speler.getPosY() + 3);// Cursor midden is posY+44.
		// Om de
		// bullet in midden te krijgen van
		// de cursor -9 = 35
	}

	public static boolean isResetBulletPos() {
		return resetBulletPos;
	}

	public static void setResetBulletPos(boolean resetBulletPoss) {
		resetBulletPos = resetBulletPoss;
	}

	public static void setSkill2(boolean skill2) {
		GameScreen.skill2 = skill2;
	}

	public static boolean isSkill3() {
		return skill3;
	}

	public static void setSkill3(boolean skill3) {
		GameScreen.skill3 = skill3;
	}

	private void setNieuweSkill() {
		vijand.setGetSkill(true);

	}

	public static boolean isResetSkill() {
		return resetSkill;
	}

	public static void setResetSkill(boolean resetSkill) {
		GameScreen.resetSkill = resetSkill;
	}

}
