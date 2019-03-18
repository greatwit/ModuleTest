package com.great.happyness.wise;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.great.happyness.evenbus.event.FileRecvEvent;
import com.great.happyness.medialib.NativeNetMedia;
import com.great.happyness.mediautils.Setting;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NdkRecvFileAllActivity extends Activity implements OnClickListener{
	
	private String TAG = "NdkEncodecActivity";
	private SurfaceHolder holder = null;
	NativeNetMedia mNetMedia = new NativeNetMedia();
	
	TextView totalLen, curLen, tvFileName, usedTime;
	Button but_recv;

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
		setContentView(R.layout.activity_recvfile_all);
		totalLen	 	= (TextView)findViewById(R.id.totalLen);
		curLen	 		= (TextView)findViewById(R.id.curLen);
		tvFileName 		= (TextView)findViewById(R.id.fileName);
		usedTime 		= (TextView)findViewById(R.id.usedTime);
		tvFileName.setText(fileName);
		
		but_recv		= (Button)findViewById(R.id.but_recv);
		but_recv.setOnClickListener(this);
		
		EventBus.getDefault().register(this);
		getFileName("/sdcard/protected");
		//mNetMedia.StartFileRecv(addr, Integer.parseInt(port), remoteFile, saveFile);
	}
	
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FileRecvEvent event) {
    	if (event != null) {
    		switch(event.getCmd()) {
    			case 0:	//recv end
    				curLen.setTextColor(Color.RED);
    				usedTime.setTextColor(Color.RED);
    				break;
    			
	    		case 1:	//read length
	    			mRecvLen += event.getLen();
	        		curLen.setText(""+mRecvLen);
	        		usedTime.setText(String.valueOf(System.currentTimeMillis()-mLastTime));
	        		break;
	        		
	    		case 2:	//file total length
    				totalLen.setText(""+event.getLen());
    				mLastTime = System.currentTimeMillis();
	    			break;
	    			
	    		case 3:	//file source close
	    			break;
    		}

    	}
    }
	
	protected void onDestroy() {
		super.onDestroy();
		mNetMedia.StopFileRecv();
		EventBus.getDefault().unregister(this);
	}
	
	public Vector<String> getFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getAbsolutePath();
                filename = filename.substring(7);
                Log.e("eee","文件名 ： " + filename);
            }else
            	getFileName(subFile[iFileLength].getAbsolutePath());
        }
        return vecFile;
    }

	private void createFile(String filepath) {
        //传入路径 + 文件名
        File mFile = new File(filepath);
        //判断文件是否存在，存在就删除
        if (mFile.exists()){
            mFile.delete();
        }
        try {
            //创建文件
            mFile.createNewFile();
            //给一个吐司提示，提示创建成功
            //Toast.makeText(getApplicationContext(), "文件创建成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public String getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis();
        //将毫秒值转换为String类型数据
        return String.valueOf(time);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.but_recv:
				mNetMedia.StopFileRecv();
				mNetMedia.StartFileRecv(addr, Integer.parseInt(port), remoteFile, saveFile);
				break;
		}
	}

}

