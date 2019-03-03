package com.great.happyness.wise;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.great.happyness.evenbus.event.FileRecvEvent;
import com.great.happyness.medialib.NativeNetMedia;
import com.great.happyness.mediautils.Setting;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class NdkRecvFileActivity extends Activity {
	
	private String TAG = "NdkEncodecActivity";
	private SurfaceHolder holder = null;
	NativeNetMedia mNetMedia = new NativeNetMedia();
	
	TextView totalLen, curLen, tvFileName, usedTime;

	Setting mDataSetting 		= new Setting();
	String addr = mDataSetting.readData(Setting.DADDR);
	String port = mDataSetting.readData(Setting.DPORT);
	String remoteFile 	= mDataSetting.readData(Setting.DFILE);
	String fileName 	= remoteFile.substring(remoteFile.lastIndexOf("/")); 
	String saveFile   	= GApplication.getRootPath() + fileName;
	
	int mRecvLen 	= 0;
	long mLastTime 	= 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recvfile);
		totalLen	 	= (TextView)findViewById(R.id.totalLen);
		curLen	 		= (TextView)findViewById(R.id.curLen);
		tvFileName 		= (TextView)findViewById(R.id.fileName);
		usedTime 		= (TextView)findViewById(R.id.usedTime);
		tvFileName.setText(fileName);
		EventBus.getDefault().register(this);
		
		mNetMedia.StartFileRecv(addr, Integer.parseInt(port), remoteFile, saveFile);
	}
	
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FileRecvEvent event) {
    	if (event != null) {
    		switch(event.getCmd()) {
    			case 0:
    				curLen.setTextColor(Color.RED);
    				usedTime.setTextColor(Color.RED);
    				break;
    			
	    		case 1:
	    			mRecvLen += event.getLen();
	        		curLen.setText(""+mRecvLen);
	        		usedTime.setText(String.valueOf(System.currentTimeMillis()-mLastTime));
	        		break;
	        		
	    		case 2:
    				totalLen.setText(""+event.getLen());
    				mLastTime = System.currentTimeMillis();
	    			break;
    		}

    	}
    }
	
	protected void onDestroy() {
		super.onDestroy();
		mNetMedia.StopFileRecv();
		EventBus.getDefault().unregister(this);
	}
	

    public String getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis();
        //将毫秒值转换为String类型数据
        return String.valueOf(time);
    }

}

