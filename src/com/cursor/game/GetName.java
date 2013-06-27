package com.cursor.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.app.AlertDialog;
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
			Intent intent = new Intent(GetName.this, StartGame.class);
			GetName.this.startActivity(intent);
		}
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
}
