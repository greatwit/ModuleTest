package com.great.happyness.wise;

import com.great.happyness.medialib.NativeMcndk;
import com.great.happyness.mediautils.AvcEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NdkDecodecActivity extends Activity implements SurfaceHolder.Callback{
	private SurfaceHolder holder = null;
	private NativeMcndk mCodec = new NativeMcndk();
	
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
		String filepath = AvcEncoder.path;//NdkEncodecActivity.mFilepath;
		mCodec.SetExtratorInt32("width", 1280);
		mCodec.SetExtratorInt32("height", 720);
		mCodec.SetExtratorInt32("durationUs", 12498744);
		mCodec.SetExtratorInt32("frame-rate", 20);
		mCodec.SetExtratorInt32("max-input-size", 38981);
		mCodec.StartH264Extrator(filepath, holder.getSurface(), 1280, 720);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//mCodec.StopFileDecodec();
		mCodec.StopH264Extrator();
	}
	
}
