package com.cs434.courseshare;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RecordService extends Service {
	private MediaRecorder mRecorder = null;
	private int UUID;
	private static String mFileName = null;
	String timeformat = "yyyy-MM-dd_HH:mm:ss";
	
	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e("RecordService", "prepare() failed");
		}
		
		mRecorder.start();
	}
	
	private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
	
	public class LocalBinder extends Binder {
        RecordService getService() {
            return RecordService.this;
        }
    }
	
	@Override
    public IBinder onBind(Intent intent){
    	return mBinder;
    }
    private final IBinder mBinder = new LocalBinder();
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
    	//SimpleDateFormat sdf = new SimpleDateFormat(timeformat, Locale.US);
    	//sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    	
    	//Create a new timestamp to create a unique recording filename
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String timestamp = dateFormat.format(new Date());
    	
    	UUID = intent.getIntExtra("UUID",-1);
    	mFileName = String.valueOf(UUID).concat("-") + timestamp + ".jpg";
    	startRecording();
    	//Continues running until explicitly stopped
        return START_STICKY;
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	stopRecording();
    	
    }
 
}
