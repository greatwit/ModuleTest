package com.great.happyness.wise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.great.happyness.medialib.NativeMcndk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NdkMcActivity extends Activity implements SurfaceHolder.Callback{
	private SurfaceHolder holder = null;
	private NativeMcndk mCodec = new NativeMcndk();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mcndk);
		SurfaceView sfv_video = (SurfaceView) findViewById(R.id.surfaceViewPlay);
		holder = sfv_video.getHolder();
		holder.addCallback(this);
	}
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		String filename = "r.mp4";
		String filepath = GApplication.getRootPath()+"/"+filename;
		if(!fileIsExists(filepath)) {
			Assets2Sd(this, filename, filepath);
		}
		mCodec.StartExtratorPlayer(filepath, holder.getSurface());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//mCodec.StopFileDecodec();
		mCodec.StopExtratorPlayer();
	}
	
    public void Assets2Sd(Context context, String fileAssetPath, String fileSdPath){
        //测试把文件直接复制到sd卡中 fileSdPath完整路径
        File file = new File(fileSdPath);
        if(file.exists()){
            file.delete();
        }

        try {
            copyBigDataToSD(context, fileAssetPath, fileSdPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void copyBigDataToSD(Context context, String fileAssetPath, String strOutFileName) throws IOException
    {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = context.getAssets().open(fileAssetPath);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }
    
  //判断文件是否存在
    public boolean fileIsExists(String strFile)
    {
        try {
            File f=new File(strFile);
            if(!f.exists())
                    return false;
            //else f.delete();
        }
        catch (Exception e) {
            return false;
        }
 
        return true;
    }
	
}
