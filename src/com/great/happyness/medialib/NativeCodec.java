package com.great.happyness.medialib;

import android.util.Log;
import android.view.Surface;

public class NativeCodec
{
	private static String TAG = NativeCodec.class.getSimpleName();
    static {
		try {
			System.loadLibrary("NativeCodec");
		}
		catch(Throwable e) {
			Log.e(TAG, "load library failed error:"+e.toString());
		}
    }
    
    public native boolean StartEncoderTest(String[] keys, Object[] values, Surface surface, int flags, String readfile, String writefile);
    public native boolean StopEncoderTest();
    
    public native boolean StartDecoderTest(String[] keys, Object[] values, Surface surface, int flags, String readfile);
    public native boolean StopDecoderTest();
}

