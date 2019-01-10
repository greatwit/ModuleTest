package com.great.happyness.ui.fragment;

import com.great.happyness.wise.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 个人中心的Fragment
 * 
 * @author yanfa06
 * 
 */
public class PersonalFragment extends Fragment
		implements OnClickListener {
	private Context mContext;
	private final String TAG = "PersonalFragment";
	private View view;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mContext).inflate(
				R.layout.fragment_personal, null);
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
