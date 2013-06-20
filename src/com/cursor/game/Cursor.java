package com.cursor.game;

import android.graphics.Rect;

public class Cursor {
	// De Niet vaste Settings
	private int posX = 400;
	private int posY = 150;
	private int speed = 8;
	private Rect cursor = new Rect(0, 0, 0, 0);
	// movement booleans
	private boolean ingedruktUp;
	private boolean ingedruktDown;
	private boolean ingedruktLeft;
	private boolean ingedruktRight;
	// De Vaste Settings
	private final int beginX = 0;
	private final int beginY = 4;
	private final int endX = 710;
	private final int endY = 348;

	public void update() {
		// Bekijkt of de cursor niet door een muur gaat
		collisionMuur();
		recUpdate();
		// Bekijkt welke movement uitgevoerd moest worden
		movement();
	}

	private void recUpdate() {
		cursor.set(posX - 40, posY - 44, posX + 40, posY + 44);
	}

	private void movement() {
		if (ingedruktUp == true) {
			moveUp();
		}
		if (ingedruktDown == true) {
			moveDown();
		}
		if (ingedruktLeft == true) {
			moveLeft();
		}
		if (ingedruktRight == true) {
			moveRight();
		}

	}

	private void collisionMuur() {
		if (posX > endX) {
			posX = endX;
		} else if (posX < beginX) {
			posX = beginX;
		}
		if (posY < beginY) {
			posY = beginY;
		} else if (posY > endY) {
			posY = endY;
		}
	}

	public void moveRight() {
		posX += speed;

	}

	public void moveLeft() {
		posX -= speed;
	}

	public void moveUp() {
		posY -= speed;
	}

	public void moveDown() {
		posY += speed;
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

	public Rect getCursor() {
		return cursor;
	}

	public void setCursor(Rect cursor) {
		this.cursor = cursor;
	}

	public boolean isIngedruktUp() {
		return ingedruktUp;
	}

	public void setIngedruktUp(boolean ingedruktUp) {
		this.ingedruktUp = ingedruktUp;
	}

	public boolean isIngedruktDown() {
		return ingedruktDown;
	}

	public void setIngedruktDown(boolean ingedruktDown) {
		this.ingedruktDown = ingedruktDown;
	}

	public boolean isIngedruktLeft() {
		return ingedruktLeft;
	}

	public void setIngedruktLeft(boolean ingedruktLeft) {
		this.ingedruktLeft = ingedruktLeft;
	}

	public boolean isIngedruktRight() {
		return ingedruktRight;
	}

	public void setIngedruktRight(boolean ingedruktRight) {
		this.ingedruktRight = ingedruktRight;
	}

}
