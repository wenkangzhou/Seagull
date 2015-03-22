package com.lazyseagull;

public interface ActivityInter {

	/**初始化控件*/
	public abstract void  findView();
	
	/**初始化事件*/
	public abstract void  addAction();
	
	/**初始化*/
	public abstract void init();
	
	/**加载参数*/
	public abstract void doPostExecute();
}
