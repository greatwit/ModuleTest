package com.great.happyness.wise;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

@SuppressWarnings("deprecation")
public class CameraUpperActivity extends Activity implements SurfaceHolder.Callback, PreviewCallback {

	private String TAG = CameraUpperActivity.class.getSimpleName();
	private final int width 	= 1280;//1920;//
	private final int height 	= 720;//1080;//
	
    private Camera mCamera;
    private Parameters parameters;
    
    private SurfaceHolder mHolder = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera_upper);
        
		SurfaceView sfv_video = (SurfaceView) findViewById(R.id.sfv_video);
		mHolder = sfv_video.getHolder();
		mHolder.addCallback(this);
    }


	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
        mCamera = getBackCamera();
        startcamera(mCamera);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        if (null != mCamera) 
        {
        	mCamera.setPreviewCallback(null);
        	mCamera.stopPreview();
        	mCamera.release();
        	mCamera = null;
        }
	}

    private void startcamera(Camera camera)
    {
        if(camera != null)
        {
            try {
            	camera.setPreviewCallback(this);
            	camera.setDisplayOrientation(90);
                if(parameters == null)
                    parameters = camera.getParameters();
  
                parameters = camera.getParameters();
                parameters.setPreviewFormat(ImageFormat.NV21);
                parameters.setPreviewSize(width, height);
                camera.setParameters(parameters);
                
                camera.setPreviewDisplay(mHolder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private Camera getBackCamera() 
	{
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return c; // returns null if camera is unavailable
    }


	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		Log.e(TAG, "data len:"+data.length);
	}
    
}
