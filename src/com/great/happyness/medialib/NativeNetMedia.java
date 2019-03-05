package com.great.happyness.medialib;

import org.greenrobot.eventbus.EventBus;

import com.great.happyness.evenbus.event.CmdEvent;
import com.great.happyness.evenbus.event.FileRecvEvent;

import android.R.string;
import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.util.Log;
import android.view.Surface;

@SuppressLint("NewApi") 
public class NativeNetMedia 
{
	private static String TAG = "NativeCodec";
	
	private static MediaCodec mCodec = null;
	
	public static  int SockID;
	
	public NativeNetMedia()
	{
	}
	
	public void onMediaCall(int sockId) {
		EventBus.getDefault().post(new CmdEvent(sockId));
		Log.e(TAG, "onMediaCall sockId:"+sockId);
		SockID = sockId;
	}
	
	public void onFileState(int sockId, int command, int fileLen) {
		EventBus.getDefault().post(new FileRecvEvent(sockId, command, fileLen));
		Log.e(TAG, "onFileState sockId:"+sockId+" command:"+command+" fileLen:"+fileLen);
		//SockID = sockId;
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
	
	public native boolean SetUpcamEncView(int sessionId);
	public native boolean StartUpcamEncodec();
	public native boolean StopUpcamEncodec();
	public native boolean UpcamEncSetInt32(String key, int value);
	public native boolean UpcamEncProvide(byte[] javaCameraFrame, int length);
	
	public native boolean StartRealView(int sockId);
    public native boolean StartRealCamCodec(Surface surface);
    public native boolean StopRealCamCodec();
    public native String  GetRealCameraParam();
    public native void 	  SetRealCameraParam(String param);
    public native boolean RealCodecSetInt32(String key, int value);
    public native boolean OpenRealCamera(int camid, String packName);
    public native boolean CloseRealCamera();
	
	
	
	public native boolean StartFileRecv(String destIp, int destPort, String remoteFile, String saveFile);
	public native boolean StopFileRecv();
	
	public native boolean StartRealVideoRecv(String destIp, int destPort, Surface surface);
	public native boolean StartFileVideoRecv(String destIp, int destPort, String remoteFile, Surface surface);
	public native boolean StopVideoRecv();

}

