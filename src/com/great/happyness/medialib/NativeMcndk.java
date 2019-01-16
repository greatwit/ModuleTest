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
	
	public native boolean StartFileDecodec(Surface surface);
	public native boolean StopFileDecodec();
}

