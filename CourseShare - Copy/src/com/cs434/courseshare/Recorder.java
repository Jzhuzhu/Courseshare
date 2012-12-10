package com.cs434.courseshare;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class Recorder extends Activity {
	private static final String LOG_TAG = "Arecorder test";
	private static String mFilename = null;
	private static int UUID;
	
	private RecordService rservice;
	private RecordButton mRecordButton = null;
	
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;
    
	private void onRecord(boolean start) {
        if (start) {
        	//Call intent here to start service, add UUID
        	Intent intent = 
            startservice();
        } else {
            stopRecording();
        }
    }
	
	private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }
	
	private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }
	
	class RecordButton extends Button {
		boolean mStartRecording = true;
		OnClickListener start = new OnClickListener(){
			public void onClick(View v) {
				onRecord(mStartRecording);
			}
			
		}
	}
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cs_recorder);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Intent intent = getIntent();
        if(intent.getIntExtra("UUID",-1) == -1){
        	//No Recording ID passed in
        	//Ping server and assign new UUID
        }
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cs_recorder, menu);
        return true;
    }   
    
}
