package com.cursor.game;

import android.graphics.Rect;

public class Bullet {
	// niet vaste waardes
	private int posX;
	private int posY;
	private int cursorPosX;
	private int cursorPosY;
	private double deltaX;
	private double deltaY;
	private Rect bullet = new Rect(0, 0, 0, 0);
	// De Vaste Settings
	private final int beginX = 0;
	private final int beginY = 4;
	private final int endX = 710;
	private final int endY = 394;

	// vaste waardes
	private final int speed = 10;

	public void update() {
		if (GameScreen.isResetBulletPos() == false) {
			berekenDeltaXY();
			GameScreen.setResetBulletPos(true);
		}
		recUpdate();
		movement();
		Collision();
	}

	private void recUpdate() {
		bullet.set(posX - 21, posY - 9, posX + 21, posY + 9);
	}

	private void Collision() {
		if (posX > endX) {
			GameScreen.setSkill1(false);// Zorgt ervoor dat skill 1 stopt
			GameScreen.setResetBulletPos(false);// Zorgt ervoor dat de
												// coordinaten straks weer
												// opgehaald worden
			GameScreen.setResetSkill(true);
		} else if (posX < beginX) {
			GameScreen.setSkill1(false);// Zorgt ervoor dat skill 1 stopt
			GameScreen.setResetBulletPos(false);// Zorgt ervoor dat de
												// coordinaten straks weer
												// opgehaald worden
			GameScreen.setResetSkill(true);
		}
		if (posY < beginY) {
			GameScreen.setSkill1(false);// Zorgt ervoor dat skill 1 stopt
			GameScreen.setResetBulletPos(false);// Zorgt ervoor dat de
												// coordinaten straks weer
												// opgehaald worden
			GameScreen.setResetSkill(true);
		}
		if(posY>endY)
		{
			GameScreen.setSkill1(false);// Zorgt ervoor dat skill 1 stopt
			GameScreen.setResetBulletPos(false);// Zorgt ervoor dat de
												// coordinaten straks weer
												// opgehaald worden
			GameScreen.setResetSkill(true);
		}
	}

	public void movement() {
		moveRightLeft();
		moveDownUp();
	}

	private void moveRightLeft() {
		posX += deltaX;
	}

	private void moveDownUp() {
		posY += deltaY;
	}

	private void berekenDeltaXY() {
		deltaX = cursorPosX - posX;
		deltaY = cursorPosY - posY;
		double hoekinterval = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		deltaX = (deltaX / hoekinterval) * speed;
		deltaY = (deltaY / hoekinterval) * speed;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Rect getBullet() {
		return bullet;
	}

	public void setBullet(Rect bullet) {
		this.bullet = bullet;
	}

	public void setCursorPosX(int cursorPosX) {
		this.cursorPosX = cursorPosX;
	}

	public void setCursorPosY(int cursorPosY) {
		this.cursorPosY = cursorPosY;
	}

}