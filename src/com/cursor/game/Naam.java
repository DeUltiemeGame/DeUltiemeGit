package com.cursor.game;
import android.app.Activity;

public class Naam extends Activity {
	static String naam;
	
	public static void setNaam(String x) {
		naam = x;
	}
	
	public static String getNaam() {
		return naam;
	}
}
