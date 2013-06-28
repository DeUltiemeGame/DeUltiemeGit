package com.cursor.game;

public class SoundOptie {
	private static float volume = 100;

	public static float getVolume() {
		return volume;
	}

	public static void setVolume(float volume) {
		SoundOptie.volume = volume;
	}
}
