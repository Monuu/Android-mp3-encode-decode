package com.example.demomp3decode;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BIgBangActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_bang);
		
		startTestMp3File();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.big_bang, menu);
		return true;
	}
	
	/**
	 * Testing encode/decode and playback a mp3file
	 */
	private void startTestMp3File() {
		Mp3Player mp3Player = new Mp3Player(this);
		byte[] encodedBytes = mp3Player.encodeMp3(R.raw.default_sound_track);
		byte[] decodedBytes = mp3Player.decodeMp3(encodedBytes);
		
		mp3Player.playMp3(decodedBytes);
	}

}
