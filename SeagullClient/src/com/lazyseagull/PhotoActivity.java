package com.lazyseagull;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class PhotoActivity extends BaseWebViewActivity {

	@Override
	public void findView() {
		super.findView();
		
	}
	
	@Override
	public void addAction() {
		super.addAction();
		Log.e("chou", "http://www.lazyseagull.com/Photo.html");
		if (MarketUtils.checkNetwork(context)) {
			homeWebView.loadUrl("http://www.lazyseagull.com/Photo.html");
		}else{
			homeWebView.loadUrl("http://www.lazyseagull.com/Photo_Offline.html");
		}
	}

	@Override
	public void doPostExecute() {

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK && homeWebView.canGoBack()) {
			
			homeWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.exit:
			finish();
			break;
		default:
			break;
		}
		return true;
	}

}
