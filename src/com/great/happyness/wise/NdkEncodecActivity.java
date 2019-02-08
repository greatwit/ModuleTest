package com.great.happyness.wise;

import com.great.happyness.medialib.NativeCamera;
import com.great.happyness.mediautils.CameraParam;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NdkEncodecActivity extends Activity implements SurfaceHolder.Callback{
	
	private String TAG = "NdkEncodecActivity";
	private SurfaceHolder holder = null;
	NativeCamera mCamera = new NativeCamera();
	
	public static String mFilepath = GApplication.getRootPath()+ "/ndkcodec.h264";
	
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
		mCamera.McndkOpenCamera(0, "com.great.happyness.wise");
		CameraParam campara = new CameraParam();
		String para = mCamera.McndkGetCameraParam();
		campara.unflatten(para);
		campara.setPreviewFormat(ImageFormat.NV21);
		campara.setPreviewSize(1280, 720);
		String flat = campara.flatten();
		Log.e(TAG, "camera para:"+flat);
		mCamera.McndkSetCameraParam(flat);
		
		mCamera.McndkSetInt32("width", 1280);
		mCamera.McndkSetInt32("height", 720);
		//mCamera.McndkSetInt32("color-format", 12498744);
		//mCamera.McndkSetInt32("max-input-size", 38981);
		mCamera.McndkSetInt32(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar);    
		mCamera.McndkSetInt32(MediaFormat.KEY_BIT_RATE, 2500000);
		mCamera.McndkSetInt32(MediaFormat.KEY_FRAME_RATE, 30);
		mCamera.McndkSetInt32(MediaFormat.KEY_I_FRAME_INTERVAL, 1);

		mCamera.StartCamndkEncodec(mFilepath, holder.getSurface());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//mCodec.StopFileDecodec();
		mCamera.McndkCloseCamera();
		mCamera.StopCamndkEncodec();
	}
	
}

