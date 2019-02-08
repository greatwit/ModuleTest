package com.great.happyness.ui.fragment;

import com.great.happyness.mediautils.Setting;
import com.great.happyness.mediautils.Utils;
import com.great.happyness.wise.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.view.ViewGroup;


public class PersonalFragment extends Fragment
		implements OnClickListener {
	
	private Context mContext;
	private final String TAG = "PersonalFragment";
	private View view;
	
	private EditText serverAddr = null, serverPort = null, filePath = null, localIp = null;
	
	Setting mDataSetting 		= new Setting();
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();

		super.onCreate(savedInstanceState);
	}

	@SuppressLint("SdCardPath")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mContext).inflate(
				R.layout.fragment_personal, null);
		serverAddr	 = (EditText)view.findViewById(R.id.serverAddr);
		serverPort	 = (EditText)view.findViewById(R.id.serverPort);
		filePath	 = (EditText)view.findViewById(R.id.filePath);
		localIp		 = (EditText)view.findViewById(R.id.localip);
		
		localIp.setText(Utils.getIPAddress());
		String addr = mDataSetting.readData(Setting.DADDR);
		String port = mDataSetting.readData(Setting.DPORT);
		String file = mDataSetting.readData(Setting.DFILE);
		
		if("".equals(addr)) {
			String tmp = "192.168.1.18";
			mDataSetting.InsertOrUpdate(Setting.DADDR, tmp);
			serverAddr.setText(tmp);
		}
			else serverAddr.setText("" + addr);
		
		if("".equals(port)) {
			String tmp = "31000";
			mDataSetting.InsertOrUpdate(Setting.DPORT, tmp);
			serverPort.setText(tmp);
		}
			else serverPort.setText("" + port);
		
		if("".equals(file)) {
			String tmp = "/sdcard/tmp.h264";
			mDataSetting.InsertOrUpdate(Setting.DFILE, tmp);
			filePath.setText(tmp);
		}
		else filePath.setText("" + file);
		
		serverAddr.addTextChangedListener(new TextWatcher() 
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				// TODO Auto-generated method stub
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				// TODO Auto-generated method stub
				Log.e(TAG, "emoteAddr: " + serverAddr.getText().toString() );
				String addr = serverAddr.getText().toString();
				mDataSetting.InsertOrUpdate(Setting.DADDR, addr);
			}
	
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
		serverPort.addTextChangedListener(new TextWatcher() 
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				// TODO Auto-generated method stub
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				// TODO Auto-generated method stub
				Log.e(TAG, "emotePort: " + serverPort.getText().toString() );
				String addr = serverPort.getText().toString();
				mDataSetting.InsertOrUpdate(Setting.DPORT, addr);
			}
	
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
		filePath.addTextChangedListener(new TextWatcher() 
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				// TODO Auto-generated method stub
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				// TODO Auto-generated method stub
				Log.e(TAG, "emotePort: " + filePath.getText().toString() );
				String addr = filePath.getText().toString();
				mDataSetting.InsertOrUpdate(Setting.DFILE, addr);
			}
	
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
		init();
		return view;
	}

	private void init() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}


