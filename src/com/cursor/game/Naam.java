package com.cursor.game;

import android.app.Activity;

public class Naam extends Activity {
	private static String naam;
	private static Boolean online = false;

	public static void setNaam(String x) {
		naam = x;
	}

	public static String getNaam() {
		return naam;
	}
	
	public static Boolean getOnline() {
		return online;
	}
	
	public static void setOnline(Boolean x) {
		online = x;
	}
}
