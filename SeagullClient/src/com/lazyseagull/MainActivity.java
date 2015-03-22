package com.lazyseagull;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	private final int TIME_UP = 1;
	
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what == TIME_UP)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, PhotoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.screen_fade, R.anim.screen_hold);
				MainActivity.this.finish();
			}
		}
	};
	
	public void onCreate(Bundle paramBundle) 
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main);

		new Thread() 
		{
			public void run() 
			{
				try 
				{
					Thread.sleep(2000);
				} 
				catch (Exception e) 
				{

				}
				Message msg = new Message();
				msg.what = TIME_UP;
				handler.sendMessage(msg);
			}
		}.start();
		
	}

}
