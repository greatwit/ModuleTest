package com.great.happyness.wise;

import com.great.happyness.medialib.NativeNetMedia;
import com.great.happyness.mediautils.CameraParam;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NdkRealServerActivity extends Activity implements SurfaceHolder.Callback{
	
	private String TAG = "NdkEncodecActivity";
	private SurfaceHolder holder = null;
	NativeNetMedia mCamera = new NativeNetMedia();

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
	
	/*
		I420: YYYYYYYY UU VV    =>YUV420P
		YV12: YYYYYYYY VV UU    =>YUV420P
		NV12: YYYYYYYY UV UV    =>YUV420SP
		NV21: YYYYYYYY VU VU    =>YUV420SP
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera.OpenRealCamera(0, "com.great.happyness.wise");
		CameraParam campara = new CameraParam();
		String para = mCamera.GetRealCameraParam();
		campara.unflatten(para);
		campara.setPreviewFormat(ImageFormat.NV21);//nv21 yyyyvu nv12 yyyyuv
		campara.setPreviewSize(1280, 720);
		String flat = campara.flatten();
		Log.e(TAG, "camera para:"+flat);
		mCamera.SetRealCameraParam(flat);
		
		mCamera.RealCodecSetInt32(MediaFormat.KEY_WIDTH, 1280);
		mCamera.RealCodecSetInt32(MediaFormat.KEY_HEIGHT, 720);
		//mCamera.McndkSetInt32("color-format", 12498744);
		mCamera.RealCodecSetInt32(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar);
		//mCamera.McndkSetInt32("max-input-size", 38981);
		//mCamera.RealCodecSetInt32(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar);    
		mCamera.RealCodecSetInt32(MediaFormat.KEY_BIT_RATE, 2500000);
		mCamera.RealCodecSetInt32(MediaFormat.KEY_FRAME_RATE, 25);
		mCamera.RealCodecSetInt32(MediaFormat.KEY_I_FRAME_INTERVAL, 2);

		mCamera.StartRealCamCodec(holder.getSurface());
		
		mCamera.StartRealView(NativeNetMedia.SockID);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mCamera.CloseRealCamera();
		mCamera.StopRealCamCodec();
	}
	
}

