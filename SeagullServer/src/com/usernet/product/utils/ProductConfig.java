package com.usernet.product.utils;

import javax.servlet.http.HttpServletRequest;

public class ProductConfig {
	
	public static final String PAGE_INDEX = "index"; //首页页面代码   用户让当行栏有选中效果
	
	public static final String PAGE_MEDIA = "media"; //自媒体页面代码
	
	public static final String PAGE_USER_ACTION = "userAction"; //用户行为分析页面代码
	
	public static final String PAGE_DATA_SERVER = "dataServer"; //数据分析服务页面代码
	
	public static final String PAGE_SETTLEMENT_MANAGER = "settlementManager"; //结算管理 页面代码
	
	public static final String PAGE_ACCOUNT_MANAGER = "accountManager"; //账户管理页面代码 
	
	public static final String PAGE_ADVERT_PUT_MANAGER = "advertPutManager"; //投放管理
	
	public static final String PAGE_SYSTEM_MANAGER = "systemManager"; //系统管理

	public static final String CHARSET = "UTF-8"; // 项目统一编码

	public static final String VERSION = "1.0.0";// 项目版本号

	public static final int RELEASE = 20101001; // 项目发布日期

	public static String realPath = null; // 真实物理地址

	public static final int DIS_STATUS = 1; // 所有不可用状态

	public static String MD5_CODE = "apktv"; // MD5较密代码

	public static final int DEFAULT_RECOMMEND = 100000;// 默认推荐顺序都为100000
	public static final int DEFAULT_AVG = 0;// 默认评分0
	public static final int DEFAULT_DOWN_COUNT = 0;// 默认下载次数0
	public static final int MAX_LOGIN_ERROR_COUNT = 5;// 登录最大错误5次

	public static final int LOGIN_FLAG_MANAGE = 1; // 管理后台登录
	public static final int LOGIN_FLAG_WEB = 2; // 网站登录
	public static final int LOGIN_FLAG_MOBILE = 3; // 手机端登录

	public static final String DEFAULT_PASSWORD = "123456";//默认密码

	public static final long DAYS_TO_TIME = 30 * 24 * 60 * 60 * 1000l; // 30天转换为毫秒(合同即将到期提示时间)

	public static final String FORM = "小编推荐";
	
	public static void setRealPath(HttpServletRequest request) {
		realPath = request.getSession().getServletContext().getRealPath("/").replaceAll("channel", "client");
	}
}
