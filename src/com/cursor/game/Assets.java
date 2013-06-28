package com.cursor.game;

import com.cursor.framework.Image;
import com.cursor.framework.Music;
import com.cursor.framework.Sound;

public class Assets {
	// Voeg hier de Image,sounds,music
	public static Image splash, background, menu, speler, vijand, bullet,
			premenu, opties;
	public static Sound jumpsound, bulletsound;
	public static Music gameover;

	public static void load(StartGame startGame) {
		gameover = startGame.getAudio().createMusic("gameover.mp3");
		gameover.setLooping(true);
		gameover.setVolume(SoundOptie.getVolume());
		gameover.play();
	}
}
