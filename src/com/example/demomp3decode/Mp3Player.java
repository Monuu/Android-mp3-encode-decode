package com.example.demomp3decode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

public class Mp3Player {

	Context mContext;
	public Mp3Player(Context context) {
		mContext = context;
	}
	
	public Uri saveRawAsMp3File() {
		
		return null;
	}
	
	public void playMp3File(Uri uri) {
		
	}
	
	/**
	 * From .mp3 to byte[]
	 * @param Rid
	 * @return
	 */
	public byte[] encodeMp3(int Rid) {
		try {
			//InputStream byteInputStream = new FileInputStream(file);
			InputStream byteInputStream = mContext.getResources().openRawResource(Rid);
			byte[] encodedFile = Base64.encode(inputStreamToByteArray(byteInputStream), Base64.DEFAULT);
			
			Log.e("hamitest", "encode: " + encodedFile);
			
			return encodedFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	/**
	 * From inputStream to byte[]
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public byte[] inputStreamToByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inStream.read(buffer)) > 0) {
            baos.write(buffer, 0, bytesRead);
        }
        
        Log.e("hamitest", "byte[]: " + baos.toByteArray());
        
        return baos.toByteArray();
    }
	
	/**
	 * decode Base64 byte[]
	 * @param encodedBytes
	 * @return
	 */
	public byte[] decodeMp3(byte [] encodedBytes) {
		byte[] decodeFile = Base64.decode(encodedBytes, Base64.DEFAULT);
		Log.e("hamitest", "decode: " + decodeFile);
		
		return decodeFile;
	}
	
	/**
	 * Playback the byte[]
	 * @param mp3SoundByteArray
	 */
	public void playMp3(byte[] mp3SoundByteArray) {
	    try {
	        // create temp file that will hold byte array
	        File tempMp3 = File.createTempFile("abba", ".mp3", Environment.getExternalStorageDirectory());
	        tempMp3.deleteOnExit();
	        FileOutputStream fos = new FileOutputStream(tempMp3);
	        fos.write(mp3SoundByteArray);
	        fos.close();

	        // Tried reusing instance of media player
	        // but that resulted in system crashes...  
	        MediaPlayer mediaPlayer = new MediaPlayer();

	        // Tried passing path directly, but kept getting 
	        // "Prepare failed.: status=0x1"
	        // so using file descriptor instead
	        FileInputStream fis = new FileInputStream(tempMp3);
	        mediaPlayer.setDataSource(fis.getFD());

	        mediaPlayer.prepare();
	        mediaPlayer.start();
	    } catch (IOException ex) {
	        String s = ex.toString();
	        ex.printStackTrace();
	    }
	}

}
