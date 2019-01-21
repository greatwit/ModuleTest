package com.great.happyness.ui.fragment;




import com.great.happyness.wise.DecodeNativeActivity;
import com.great.happyness.wise.DecodeUpperActivity;
import com.great.happyness.wise.EncodeUpperActivity;
import com.great.happyness.wise.EncodeNativeActivity;
import com.great.happyness.wise.NdkMcActivity;
import com.great.happyness.wise.CameraNativeActivity;
import com.great.happyness.wise.NativeMediaRecorderActivity;
import com.great.happyness.wise.NdkDecodecActivity;
import com.great.happyness.wise.NdkEncodecActivity;
import com.great.happyness.wise.R;
import com.great.happyness.wise.CameraUpperActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 主页的Fragment
 * 
 * @author yanfa06
 * 
 */
public class HomeFragment extends Fragment
		implements OnClickListener{
	private final String TAG = "HomeFragment";
	private Context mContext;
	private View view;
	private Button camera_upper = null, camera_native = null, play_rec = null, 
			play_ext = null, encode_upper = null, decode_upper = null, 
			encode_native = null, decode_native = null, mcndk_encoder = null, mcndk_decoder = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();
		// 注册EventBus
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
		camera_upper = (Button)view.findViewById(R.id.camera_upper);
		camera_upper.setOnClickListener(this);
		camera_native = (Button)view.findViewById(R.id.camera_native);
		camera_native.setOnClickListener(this);
		
		encode_upper = (Button)view.findViewById(R.id.encode_upper);
		encode_upper.setOnClickListener(this);
		decode_upper = (Button)view.findViewById(R.id.decode_upper);
		decode_upper.setOnClickListener(this);
		
		encode_native = (Button)view.findViewById(R.id.encode_native);
		encode_native.setOnClickListener(this);
		decode_native = (Button)view.findViewById(R.id.decode_native);
		decode_native.setOnClickListener(this);
		
		play_rec = (Button)view.findViewById(R.id.play_rec);
		play_rec.setOnClickListener(this);
		play_ext = (Button)view.findViewById(R.id.play_ext);
		play_ext.setOnClickListener(this);

		mcndk_encoder = (Button)view.findViewById(R.id.mcndk_encoder);
		mcndk_encoder.setOnClickListener(this);
		mcndk_decoder = (Button)view.findViewById(R.id.mcndk_decoder);
		mcndk_decoder.setOnClickListener(this);
		
//		btnNativeRec = (Button)view.findViewById(R.id.btnNativeRec);
//		btnNativeRec.setOnClickListener(this);
//		
//		btnExtractor = (Button)view.findViewById(R.id.btnExtractor);
//		btnExtractor.setOnClickListener(this);
//		
//		btnCamCodec = (Button)view.findViewById(R.id.btnCamCodec);
//		btnCamCodec.setOnClickListener(this);
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch(v.getId())
		{
			case R.id.camera_upper:
				intent.setClass(mContext, CameraUpperActivity.class);
				startActivity(intent);
				break;
			case R.id.camera_native:
				intent.setClass(mContext, CameraNativeActivity.class);
				startActivity(intent);
				break;
				
			case R.id.encode_upper:
				intent.setClass(mContext, EncodeUpperActivity.class);
				startActivity(intent);
				break;
			case R.id.decode_upper:
				intent.setClass(mContext, DecodeUpperActivity.class);
				startActivity(intent);
				break;
				
			case R.id.encode_native:
				intent.setClass(mContext, EncodeNativeActivity.class);
				startActivity(intent);
				break;
			case R.id.decode_native:
				intent.setClass(mContext, DecodeNativeActivity.class);
				startActivity(intent);
				break;
				
			case R.id.play_rec:
				intent.setClass(mContext, NativeMediaRecorderActivity.class);//
				startActivity(intent);
				break;
			case R.id.play_ext:
				intent.setClass(mContext, NdkMcActivity.class);
				startActivity(intent);
				break;
				
			case R.id.mcndk_encoder:
				intent.setClass(mContext, NdkEncodecActivity.class);//
				startActivity(intent);
				break;
				
			case R.id.mcndk_decoder:
				intent.setClass(mContext, NdkDecodecActivity.class);//
				startActivity(intent);
				break;
				
//			case R.id.btnNativeRec:
//				intent.setClass(mContext, NativeMediaRecorderActivity.class);
//				startActivity(intent);
//				break;
//				
//			case R.id.btnExtractor:
//				intent.setClass(mContext, NativeCameraActivity.class);//ExtractorActivity
//				startActivity(intent);
//				break;
//				
//			case R.id.btnCamCodec:
//				intent.setClass(mContext, UpperCameraActivity.class);
//				startActivity(intent);
//				break;
		}
	}

}
