	package com.cursor.game;

	import android.content.Intent;

import com.cursor.framework.Screen;
import com.cursor.framework.implementation.AndroidGame;

	public class StartGame extends AndroidGame {
	    boolean firstTimeCreate = true;
	    
	    protected void onCreate() {
	    	Intent intent = getIntent();
	    	String naam = intent.getStringExtra("naam");
	    	System.out.println(naam); 
	    } 

	    @Override
	    public Screen getInitScreen() {

	        if (firstTimeCreate) {
	            Assets.load(this);
	            firstTimeCreate = false;
	        }

	        return new SplashScreen(this);

	    }

	    @Override
	    public void onBackPressed() {
	        getCurrentScreen().backButton();
	    }

	    @Override
	    public void onResume() {
	        super.onResume();       

	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	    }
	}
