package com.great.happyness.wise;

import java.util.HashMap;
import java.util.Map;

import com.great.happyness.medialib.NativeCodec;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Bundle;
import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


public class EncodeNativeActivity extends Activity implements SurfaceHolder.Callback {

	private String TAG = EncodeNativeActivity.class.getSimpleName();
	private final int width 	= 1280;//1920;//
	private final int height 	= 720;//1080;//
    
    private SurfaceHolder mHolder = null;
    private NativeCodec mCodec = new NativeCodec();
    
	final String KEY_MIME  = "mime";
    final String KEY_WIDTH = "width";
    final String KEY_HEIGHT = "height";
    String[] keys 	= null;
    Object[] values = null; 
    
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
		Map<String, Object> mMap = new HashMap();
		mMap.put(KEY_MIME, "video/avc");
		mMap.put(KEY_WIDTH, new Integer(width));
		mMap.put(KEY_HEIGHT, new Integer(height));
		mMap.put(MediaFormat.KEY_COLOR_FORMAT, new Integer(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar)); 
		mMap.put(MediaFormat.KEY_BIT_RATE, new Integer(width*height*5));
		mMap.put(MediaFormat.KEY_FRAME_RATE, new Integer(20));
		mMap.put(MediaFormat.KEY_I_FRAME_INTERVAL, new Integer(2));
		
        keys 	= new String[mMap.size()];
        values 	= new Object[mMap.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry: mMap.entrySet()) {
            keys[i] 	= entry.getKey();
            values[i] 	= entry.getValue();
            ++i;
        } 
        String readpath = GApplication.getRootPath()+"/"+"720p.yuv";
        String writepath = GApplication.getRootPath()+"/"+"720p.h264";
        mCodec.StartEncoderTest(keys, values, holder.getSurface(), MediaCodec.CONFIGURE_FLAG_ENCODE, readpath, writepath);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCodec.StopEncoderTest();
	}
    
}
