package com.lazyseagull;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebStorage.QuotaUpdater;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


public abstract class BaseWebViewActivity extends BaseActivity {
	
	protected Config menu;
	protected WebView homeWebView;
	protected RelativeLayout notNetworkLayout;
	protected ProgressBar loading;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				Toast.makeText(context, "请检查网络是否正常连接!", Toast.LENGTH_SHORT).show(); 
				/*
				homeWebView.setVisibility(View.GONE);
				notNetworkLayout.setVisibility(View.VISIBLE);
				*/
			}
		};

	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		menu = new Config(context);
		setContentView(R.layout.webview);
		super.onCreate(savedInstanceState);
	}
	@Override
	public void findView() {
		homeWebView = (WebView) findViewById(R.id.home_webview);
		loading = (ProgressBar) findViewById(R.id.loading);
		notNetworkLayout = (RelativeLayout) findViewById(R.id.nonetworkLayout);
		homeWebView.getSettings().setJavaScriptEnabled(true);
		homeWebView.getSettings().setSupportZoom(false);
		homeWebView.getSettings().setBuiltInZoomControls(false);
		// 设置可以访问文件
		homeWebView.getSettings().setAllowFileAccess(true);
		// 使用localStorage则必须打开
		//设置HTML5离线支持
		homeWebView.getSettings().setDomStorageEnabled(true);
		homeWebView.getSettings().setAppCacheMaxSize(1024*1024*8);//设置缓冲大小，我设的是8M 
		String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath(); 
		homeWebView.getSettings().setAppCachePath(appCacheDir); 
		homeWebView.getSettings().setAllowFileAccess(true); 
		homeWebView.getSettings().setAppCacheEnabled(true); 
		homeWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); 
		// 设置缓存
		//homeWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		//启用数据库
		homeWebView.getSettings().setDatabaseEnabled(true); 
		// 数据库目录
		String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
		// 设置数据库路径
		homeWebView.getSettings().setDatabasePath(dir);
		
		// 网页JS与客户端交互的地方
		homeWebView.addJavascriptInterface(new MyJavaScript(), "myAndroidJs");
		
		// 如果没有网络则给定一个提示
		if (MarketUtils.checkNetwork(context)) {

			homeWebView.setVisibility(View.VISIBLE);
			notNetworkLayout.setVisibility(View.GONE);
		} else {
			/*
			homeWebView.setVisibility(View.GONE);
			notNetworkLayout.setVisibility(View.VISIBLE);
			// 提醒用户设置网络
			NetworkManager.showNetworkSetting(context);
			*/
			Toast.makeText(context, "进入离线模式!", Toast.LENGTH_LONG).show(); 
		}
		homeWebView.setWebChromeClient(new WebChromeClient() {

			// 扩充数据库的容量
			@Override
			public void onExceededDatabaseQuota(String url,
					String databaseIdentifier, long currentQuota,
					long estimatedSize, long totalUsedQuota,
					QuotaUpdater quotaUpdater) {

				super.onExceededDatabaseQuota(url, databaseIdentifier,
						currentQuota, estimatedSize, totalUsedQuota,
						quotaUpdater);

				quotaUpdater.updateQuota(estimatedSize * 2);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				new AlertDialog.Builder(context)
						.setTitle("友情提示")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								}).setCancelable(false).create().show();

				return true;
			}

			@Override
			public boolean onJsConfirm(WebView view, String url,
					String message, final JsResult result) {

				new AlertDialog.Builder(context)
						.setTitle("友情提示")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								})
						.setNegativeButton(android.R.string.cancel,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.cancel();
									}
								}).setCancelable(true).create().show();
				return true;
			}
		});
		homeWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {

				super.onPageStarted(view, url, favicon);
				loading.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				loading.setVisibility(View.GONE);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

				view.stopLoading();
				view.clearView();
				Message msg = handler.obtainMessage();// 发送通知，加入线程
				msg.what = 1;// 通知加载自定义404页面
				handler.sendMessage(msg);// 通知发送！
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

		});
	}
	@Override
	public void addAction() {

		notNetworkLayout.setOnClickListener(new WebReload());
	}
	/***
	 * 自定义JS调用事件
	 * 
	 * @author Administrator
	 * 
	 */
	class WebReload implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			homeWebView.reload();
			if (MarketUtils.checkNetwork(context)) {

				homeWebView.setVisibility(View.VISIBLE);
				notNetworkLayout.setVisibility(View.GONE);
			} else {
				/*
				homeWebView.setVisibility(View.GONE);
				notNetworkLayout.setVisibility(View.VISIBLE);
				*/
				//Toast.makeText(context, "请检查网络是否连接!", Toast.LENGTH_SHORT).show(); 
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复

			new AlertDialog.Builder(context)
					.setIcon(R.drawable.manager)
					.setTitle("退出")
					.setMessage(menu.getExitText())
					.setPositiveButton(android.R.string.ok,
							new AlertDialog.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new AlertDialog.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).setCancelable(true).create().show();

		}
		return super.onKeyDown(keyCode, event);
	}	
	class MyJavaScript {
		// 用于详细页面点击返回
		public void finishOrGoback() {
			if (homeWebView.canGoBack()) {
				homeWebView.goBack();// 返回前一个页面
			} else {
				finish();
			}
		}
		// 清理缓存
		public void cleanCache() {
			// 提示对话框
			AlertDialog.Builder builder = new Builder(context);
			builder.setTitle("清理缓存")
					.setMessage("是要清理缓存吗?")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									MarketUtils.cleanWebview(context);
									Toast.makeText(context, "清理成功！", Toast.LENGTH_LONG).show(); 
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).show();
			}
		//用浏览器打开
		public void openBrowser(String url){
			   //urlText是一个文本输入框，输入网站地址
			   //Uri  是统一资源标识符
			   Log.e("url", url);
			   Uri  uri = Uri.parse(url.toString());
			   Intent  intent = new  Intent(Intent.ACTION_VIEW, uri);
			   startActivity(intent);
			}
		//分享
		public void onClickShare(String url,String text) {  	  
	        Intent intent=new Intent(Intent.ACTION_SEND);   
	        intent.setType("image/*");   
	        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");   
	        intent.putExtra(Intent.EXTRA_TEXT, text+" FROM [海鸥] http://www.lazyseagull.com");    
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	        startActivity(Intent.createChooser(intent, getTitle()));   
	    }
		//获取网络状况
		public int getNetStatus() {  
			if (MarketUtils.checkNetwork(context)) 
				return 0;		
			else{
				Toast.makeText(context, "请检查网络是否正常连接!", Toast.LENGTH_SHORT).show(); 
				return 1;	
			}	
				
		}
	}


}
