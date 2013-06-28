package com.cursor.game;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;

public class GetName extends Activity {
	Editable editable;
	String naam;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getname);

		if (getName() == null) {

			final EditText input = new EditText(GetName.this);
			AlertDialog.Builder dialog = new AlertDialog.Builder(GetName.this);
			dialog.setMessage("Choose a nickname: ");
			dialog.setView(input);
			dialog.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							editable = input.getText();
							naam = editable.toString();
							SharedPreferences sp = getSharedPreferences(
									"SharedPrefs", MODE_PRIVATE);
							Editor editor = sp.edit();
							editor.putString("naam", naam);
							editor.commit();

							Intent intent = new Intent(GetName.this,
									StartGame.class);
							GetName.this.startActivity(intent);
						}
					}).show();
		} else {

			if (isOnline()) {

				AlertDialog.Builder dialog = new AlertDialog.Builder(
						GetName.this);
				dialog.setMessage("Do you want to donate?");
				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								Intent browserIntent = new Intent(
										Intent.ACTION_VIEW,
										Uri.parse("http://145.24.222.174/donate"));
								startActivity(browserIntent);

							}
						});
				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								Intent intent = new Intent(GetName.this,
										StartGame.class);
								GetName.this.startActivity(intent);

							}
						}).show();
			} else {

				AlertDialog.Builder dialog = new AlertDialog.Builder(
						GetName.this);
				dialog.setTitle("Offline Mode");
				dialog.setMessage("No highscores will be saved");
				dialog.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent = new Intent(GetName.this,
										StartGame.class);
								GetName.this.startActivity(intent);
							}
						}).show();

			}
		}

	}

	public void resetNaam() {
		SharedPreferences sp = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("reset", true);
		editor.commit();
		Boolean reset = getSharedPreferences("SharedPrefs", MODE_PRIVATE)
				.getBoolean("reset", false);
		System.out.println("Reset: " + reset);
	}

	public String getName() {
		String naam = getSharedPreferences("SharedPrefs", MODE_PRIVATE)
				.getString("naam", null);
		return naam;
	}

	public void onPause() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public static SharedPreferences getSharedPreferences(Context ctxt) {
		return ctxt.getSharedPreferences("FILE", 0);
	}

	public Boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			SharedPreferences sp = getSharedPreferences(
					"SharedPrefs", MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putBoolean("online", false);
			editor.commit();
			return false;
		} else {
			SharedPreferences sp = getSharedPreferences(
					"SharedPrefs", MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putBoolean("online", true);
			editor.commit();
			return true;
		}
	}
}
