package com.great.happyness.wise;


import java.io.File;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Environment;
import android.util.Log;


public class GApplication extends Application {
	
	String TAG = "GApplication";
	
	static String ROOTPATH = "ModuleTest";
	
	public static GApplication mContext = null;
	
    @Override
    public void onCreate() {
        // 程序创建的时候执行
        Log.d(TAG, "onCreate");
        mContext = this;
        
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在 
        if (sdCardExist) 
        	isExist(getRootPath());
        
        super.onCreate();
    }
    
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");
        super.onTerminate();
    }
    
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }
    
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
    
    public static synchronized String getAppName() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
            		mContext.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return mContext.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRootPath() {
    	return Environment.getExternalStorageDirectory().toString()+"/"+getAppName();
    }
    
    /**
    * 
    * @param path 文件夹路径
    */
    public static void isExist(String path) {
	    File file = new File(path);
	    //判断文件夹是否存在,如果不存在则创建文件夹
	    if (!file.exists()) {
	    	file.mkdir();
	    }
    }
    
}


