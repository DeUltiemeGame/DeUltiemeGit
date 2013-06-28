package com.cursor.game;

import android.graphics.Rect;

public class Enemy {
	// Vaste waardes
	private final int beginX = 0;
	private final int beginY = 4;
	private final int endX = 710;
	private final int endY = 394;
	private final int skillTijd = 100;
	private final int speedDown = 8;
	// niet vaste waardes
	private int posX = 324;
	private int posY = 388;
	private int cursorPosY;
	private int cursorPosX;
	private int speedX = 4;
	private double deltaX;
	private double deltaY;
	private final double speed = 8;
	private int movementtimer = 100;
	private int randomMovement = 0;
	private int skillTimer = 0;
	private Rect enemy = new Rect(0, 0, 0, 0);
	private boolean resetPos;
	private boolean collision = false;
	private boolean getSkill = true;
	private boolean playSound = true;
	// skill 3 booleans
	private boolean collisionSkill3RightLeft = false;
	private boolean collisionSkill3DownUp = false;
	// skill 4 booleans
	// skill 1 booleans
	private boolean posUp = false;
	private boolean posDown = true;

	public void update() {
		collision();
		movement();
		skills();
		recUpdate();
		soundCheck();
	}

	private void soundCheck() {
		if (GameScreen.getSkill1() == false && GameScreen.isSkill2() == false
				&& GameScreen.isSkill3() == false) {
			playSound = true;
		}
		if (GameScreen.getSkill1() == true) {
			if (playSound == true) {
				Assets.bulletsound.play(SoundOptie.getVolume());
				playSound = false;
			}
		} else if (GameScreen.isSkill2() == true) {
			if (playSound == true) {
				Assets.jumpsound.play(SoundOptie.getVolume());
				playSound = false;
			}
		} else if (GameScreen.isSkill3() == true) {
			if (playSound == true) {
				Assets.jumpsound.play(SoundOptie.getVolume());
				playSound = false;
			}
		}

	}

	private void recUpdate() {
		enemy.set(posX - 37, posY - 41, posX + 37, posY + 41);
	}

	private void skills() {
		if (getSkill == true) {
			if (skillTimer >= skillTijd) {
				int randomnum = 0 + (int) (Math.random() * 3);
				switch (randomnum) {
				case 0:
					GameScreen.setSkill1(true);
					break;
				case 1:
					collision = false;
					GameScreen.setSkill2(true);
					break;
				case 2:
					GameScreen.setSkill3(true);
					break;
				}
				skillTimer = 0;

			} else {
				skillTimer++;
			}
		}
	}

	public void jumpSkill() {
		if (resetPos == false) {
			berekenDeltaXY();
			kijkUpDown();// kijkt of enemy boven of onder is.
			resetPos = true;
		}
		if (collision == false) {
			moveRightLeft();
			moveDownUp();
		} else if (posDown == true && collision == true) {
			moveDown();
		} else if (posUp == true && collision == true) {
			moveUp();
		}
		collisionSkill();
		recUpdate();
	}

	public void kijkUpDown() {
		if (posY < 240) {
			posUp = true;
			posDown = false;
		} else {
			posUp = false;
			posDown = true;
		}

	}

	private void collisionSkill() {
		if (posX > endX) {
			collision = true;
			getSkill = true;

		} else if (posX < beginX) {
			collision = true;
			getSkill = true;
		}
		if (posY < beginY) {
			getSkill = true;
			collision = true;
		} else {
			if (posY > endY) {
				getSkill = true;
				collision = true;
			}
		}
	}

	private void moveDown() {
		if (posY <= endY) {
			posY += speedDown;
		} else {
			GameScreen.setSkill2(false);
			resetPos = false;
		}
	}

	private void moveUp() {
		if (posY >= endY) {
			posY -= speedDown;
		} else {
			GameScreen.setSkill2(false);
			resetPos = false;
		}

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

	private void collision() {
		if (posX > endX) {
			posX = endX;
			// zorgt ervoor dat de enemy niet tegen de muur blijft maar andere
			// kant weer op gaat
			randomMovement++;
		} else if (posX < beginX) {
			posX = beginX;
			// zorgt ervoor dat de enemy niet tegen de muur blijft maar andere
			// kant weer op gaat
			randomMovement--;
		}
		if (posY < beginY) {
			posY = beginY;
		} else if (posY > endY) {
			posY = endY;
		}
	}

	private void movement() {
		if (movementtimer == 100) {
			randomMovement = 0 + (int) (Math.random() * 2);
			movementtimer = 0;
		} else {
			movementtimer++;
		}
		switch (randomMovement) {
		case 0:
			moveRight();
			break;
		case 1:
			moveLeft();
			break;
		}
	}

	private void moveRight() {
		posX += speedX;
	}

	private void moveLeft() {
		posX -= speedX;
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

	public Rect getEnemy() {
		return enemy;
	}

	public int getCursorPosY() {
		return cursorPosY;
	}

	public void setCursorPosY(int cursorPosY) {
		this.cursorPosY = cursorPosY;
	}

	public int getCursorPosX() {
		return cursorPosX;
	}

	public void setCursorPosX(int cursorPosX) {
		this.cursorPosX = cursorPosX;
	}

	public boolean isResetPos() {
		return resetPos;
	}

	public void setResetPos(boolean resetPos) {
		this.resetPos = resetPos;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public void setGetSkill(boolean getSkill) {
		this.getSkill = getSkill;
	}

	public void skill3() {
		if (resetPos == false) {
			berekenDeltaXY();
			resetPos = true;
		}
		if (collisionSkill3RightLeft == false) {
			moveRightLeft();
		}
		if (collisionSkill3DownUp == false) {
			moveDownUp();
		}
		collisionSkill3();
	}

	private void collisionSkill3() {
		if (posX > endX) {
			posX = endX;

			if (posY > 240) {
				moveDown();
			} else {
				moveUp();
			}

		} else if (posX < beginX) {
			posX = beginX;
			GameScreen.setSkill3(false);
			resetPos = false;
			getSkill = true;

		}
		if (posY < beginY) {
			posY = beginY;
			GameScreen.setSkill3(false);
			resetPos = false;
			getSkill = true;

		} else if (posY > endY) {
			posY = endY;
			GameScreen.setSkill3(false);
			resetPos = false;
			getSkill = true;
		}
	}

	public boolean isGetSkill() {
		return getSkill;
	}

	public boolean isPosUp() {
		return posUp;
	}

	public boolean isPosDown() {
		return posDown;
	}

}
