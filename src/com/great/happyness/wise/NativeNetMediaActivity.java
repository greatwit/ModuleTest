package com.great.happyness.wise;

import com.great.happyness.medialib.NativeNetMedia;
import com.great.happyness.mediautils.AvcEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NativeNetMediaActivity extends Activity implements SurfaceHolder.Callback{
	private SurfaceHolder holder = null;
	NativeNetMedia mNetMedia = new NativeNetMedia();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mcndk);
		SurfaceView sfv_video = (SurfaceView) findViewById(R.id.surfaceViewPlay);
		holder = sfv_video.getHolder();
		holder.addCallback(this);
	}
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		String filepath = AvcEncoder.path;
		mNetMedia.StartVideoView("192.168.100.67", 31000, "/sdcard/camera_1280x720.h264", holder.getSurface());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mNetMedia.StopVideoView();
	}
	
}
