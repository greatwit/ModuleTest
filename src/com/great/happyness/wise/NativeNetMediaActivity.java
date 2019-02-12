package com.great.happyness.wise;

import com.great.happyness.medialib.NativeNetMedia;
import com.great.happyness.mediautils.Setting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NativeNetMediaActivity extends Activity implements SurfaceHolder.Callback{
	private final String TAG = "NativeNetMediaActivity";
	
	private SurfaceHolder holder = null;
	NativeNetMedia mNetMedia = new NativeNetMedia();
	
	Setting mDataSetting 		= new Setting();
	String addr = mDataSetting.readData(Setting.DADDR);
	String port = mDataSetting.readData(Setting.DPORT);
	String file = mDataSetting.readData(Setting.DFILE);
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
		Log.e(TAG, "addr:"+addr+" port:"+port+" file:"+file);
		mNetMedia.StartRealVideoRecv(addr, Integer.parseInt(port), holder.getSurface());
		//mNetMedia.StartFileVideoRecv(addr, Integer.parseInt(port), file, holder.getSurface());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mNetMedia.StopVideoRecv();
	}
	
}
