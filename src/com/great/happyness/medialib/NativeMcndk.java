package com.great.happyness.medialib;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.util.Log;
import android.view.Surface;

@SuppressLint("NewApi") 
public class NativeMcndk 
{
	private static String TAG = "NativeCodec";
	
	private static MediaCodec mCodec = null;
	
	public NativeMcndk()
	{
	}
	
	static
	{
		try
		{
			System.loadLibrary("mcndk");
			//System.loadLibrary("netcodec");  //
		}
		catch(Throwable e)
		{
			Log.e(TAG, "loadLibrary:"+e.toString());
		}
	}
	public native boolean StartExtratorPlayer(String filepath, Surface surface);
	public native boolean StopExtratorPlayer();
	
	public native boolean StartH264Extrator(String filepath, Surface surface, int w, int h);
	public native void 	  SetExtratorInt32(String key, int value);
	public native boolean StopH264Extrator();
	
	public native boolean StartH264Decodec(String filepath, Surface surface, int w, int h);
	public native void 	  SetDecodecInt32(String key, int value);
	public native boolean StopH264Decodec();
}

