package com.great.happyness.medialib;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.util.Log;
import android.view.Surface;

@SuppressLint("NewApi") 
public class NativeNetMedia 
{
	private static String TAG = "NativeCodec";
	
	private static MediaCodec mCodec = null;
	
	public NativeNetMedia()
	{
	}
	
	static
	{
		try
		{
			System.loadLibrary("netmedia");
			//System.loadLibrary("netcodec");  //
		}
		catch(Throwable e)
		{
			Log.e(TAG, "loadLibrary:"+e.toString());
		}
	}
	
	public native boolean StartNetWork();
	public native boolean StopNetWork();
	public native boolean StartServer(String bindIp, int bindPort);
	public native boolean StopServer();
	
	public native boolean StartFileRecv(String destIp, int destPort, String remoteFile, String saveFile);
	public native boolean StopFileRecv();
	public native boolean StartVideoView(String destIp, int destPort, String remoteFile, Surface surface);
	public native boolean StopVideoView();

}

