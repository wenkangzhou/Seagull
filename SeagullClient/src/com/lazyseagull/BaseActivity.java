package com.lazyseagull;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

public abstract class  BaseActivity extends Activity implements ActivityInter{
	
	protected LayoutInflater inflater;
	
	protected Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		findView();
		addAction();
		init();
		doPostExecute();
	}
	
	public Context getContext(){
		return this;
	}
	public void init(){
		inflater=LayoutInflater.from(this);
	}
}
