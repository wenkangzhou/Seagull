function changePageSize(pageSize) {

	document.getElementById("pageSize").value = pageSize;
	document.getElementById("queryform").submit();
	// window.location = url + "&pageSize=" + pageSize;
}

function changePage(pageNo, totalPage, pageSize) {
	if (typeof (pageNo) == "string") {
		pageNo = document.getElementById(pageNo).value;
	}
	if (pageNo <= totalPage && pageNo > 0) {

		document.getElementById("pageNo").value = pageNo;
		document.getElementById("pageSize").value = pageSize;
		document.getElementById("queryform").submit();
		// window.location = url + "&pageNo=" + pageNo + "&pageSize=" +
		// pageSize;
	}
}

/**
 * 获得当前时间(yyyy-MM-dd hh:mm:ss)
 * 
 * @show:显示控件ID
 */
function getNowTime(show) {
	var date = new Date();
	var time = formateYear(date.getYear()) + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":" + date.getMinutes()
			+ ":" + date.getSeconds();
	var show = document.getElementById(show);
	if (show) {
		show.value = time;
	} else {
		alert('没有该控件');
	}
	function formateYear(year) {
		if (year < 1990) {
			return year + 1900;
		} else {
			return year;
		}
	}
}
/**
 * 获得当前时间(yyyy-MM-dd)
 * 
 * @show:显示控件ID
 */
function getNowDate(show) {
	var date = new Date();
	var time = formateYear(date.getYear()) + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
	var show = document.getElementById(show);
	if (show) {
		show.value = time;
	} else {
		alert('没有该控件');
	}
	function formateYear(year) {
		if (year < 1990) {
			return year + 1900;
		} else {
			return year;
		}
	}
}
/**
 * 生成编号
 * 
 * @id:控件显示ID
 */
function createNO(id) {
	var date = new Date();
	var no = randomID() + randomID() + "-";
	var year = new String(date.getYear());
	var month = format(new String(date.getMonth() + 1));
	var day = format(new String(date.getDate()));
	var hour = format(new String(date.getHours()));
	var minute = format(new String(date.getMinutes()));
	var seconds = format(new String(date.getSeconds()));
	var milliseconds = format(new String(date.getMilliseconds()));
	no += year + month + day + hour + minute + seconds + milliseconds;

	var showNO = document.getElementById(id);
	showNO.style.color = "blue";
	showNO.value = no;
	return no;
	function format(value) {
		var string = value;
		if (value.length == 1) {
			string = "0" + string;
		}
		return string;
	}
	function randomID() {
		var value = "A@B@C@D@E@F@G@H@I@J@K@L@M@N@O@P@Q@R@S@T@U@V@W@X@Y@Z";
		var array = value.split("@");
		var index = Math.random() * 26;
		var no = array[Math.round(index)];
		if (value.indexOf(no) == -1) {
			return randomID();
		}
		return no;
	}
}
/**
 * 日期是否合法
 */
function checkDate(str) {
	var regex = new RegExp(
			"^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
	var dateValue = str;
	if (!regex.test(dateValue)) {
		return false;
	}
	return true;

}
/**
 * 身份证是否合法
 */
function checkIdCard(str) {
	// 身份证正则表达式(15位)
	isIDCard1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	// 身份证正则表达式(18位)
	isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	// 验证身份证，返回结果
	return (isIDCard1.test(str) || isIDCard2.test(str));
}
/**
 * 是否为数字
 */
function checkNum(str) {
	var regex = new RegExp("^[0-9]*$");
	if (!regex.test(str)) {
		return false;
	}
	return true;
}
/**
 * 是否为金额
 */
function checkNumber(str) {
	var regex = new RegExp("^[0-9]*(\.[0-9]{1,2})?$");
	if (!regex.test(str)) {
		return false;
	}
	return true;
}
/**
 * 电话是否合法
 */
function checkPhone(str) {
	var reg = /^0*(13|15)\d{9}$/;
	return reg.test(str);
}
/**
 * email是否合法
 */
function checkEmail(str) {
	var regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (!regex.test(str)) {
		return false;
	}
	return true;
}
/**
 * 邮编是否合法
 */
function checkPostCode(str) {
	var regex = /\d{6}/;
	if (!regex.test(str)) {
		return false;
	}
	return true;
}

function changeback() {
	if (event.fromElement.contains(event.toElement)
			|| source.contains(event.toElement) || source.id == "nc")
		return

	if (event.toElement != source && cs[1].style.backgroundColor != clickcolor)
		for ( var i = 0; i < cs.length; i++) {
			cs[i].style.backgroundColor = "";
		}
}

function clickto() {
	source = event.srcElement;
	if (source.tagName == "TR" || source.tagName == "TABLE")
		return;
	while (source.tagName != "TD")
		source = source.parentElement;
	source = source.parentElement;
	cs = source.children;
	if (cs[1].style.backgroundColor != clickcolor && source.id != "nc")
		for ( var i = 0; i < cs.length; i++) {
			cs[i].style.backgroundColor = clickcolor;
		}
	else
		for ( var i = 0; i < cs.length; i++) {
			cs[i].style.backgroundColor = "";
		}
}
function mout(t) {
	t.style.backgroundColor = '';
}
function mover(t) {
	t.style.backgroundColor = clickcolor;
}

function $(_id) {
	return document.getElementById(_id);
}
/**
 * js校验文件后缀名
 */
function checkPic(v) {
	var last = v.substring(v.lastIndexOf(".") + 1);
	// 添加需要判断的后缀名类型
	var tp = "png|jpg|PNG|JPG";

	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last.toLocaleString());
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	if (rs >= 0) {
		return true;
	} else {
		return false;
	}
}
function checkApk(v) {
	var last = v.substring(v.lastIndexOf(".") + 1);
	// 添加需要判断的后缀名类型
	var tp = "apk|APK";

	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last.toLocaleString());
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	if (rs >= 0) {
		return true;
	} else {
		return false;
	}
}

function checkMusic(v) {
	var last = v.substring(v.lastIndexOf(".") + 1);
	// 添加需要判断的后缀名类型
	var tp = "imy|mp3|mid|wma|wav|aac|m4a|ogg|amr|rm|ram";

	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last.toLowerCase());
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	if (rs >= 0) {
		return true;
	} else {
		return false;
	}
}

/** 是否为合法的登录名或密码 */
function isLoginName(s) {
	var patrn = /^(\w){6,20}$/;
	if (!patrn.exec(s))
		return false;
	return true;
}

/* 移除已选择的渠道DIV */
function removeSelectedChannelDiv(channel) {

	document.getElementById("channelCheckBox" + channel).checked = false;

	document.getElementById("divSelected" + channel).style.display = "none";
}

function csubstr(str, len) {
	// 文字省略号
	if (str.length > 10) {
		return str.substring(0, len) + "...";
	} else {
		return str;
	}
}
