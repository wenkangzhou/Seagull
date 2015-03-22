var _MsgContent;
var _contact;
var _log;
var db;

/** 判断浏览器是否支持WebSql数据库* */
function getOpenDatabase() {
	try {
		// 如果支持则返回数据库连接句柄
		if (!!window.openDatabase) {
			return window.openDatabase;
		} else {
			return undefined;
		}
	} catch (e) {
		return undefined;
	}
}

/* 获取界面控件引用 */
function initView() {
	// 反馈内容
	_MsgContent = document.getElementById("MsgContent");
	// 联系方式
	_contact = document.getElementById("contact");
	// 日志
	_log = document.getElementById("log");

}

/* 页面加载完成时调用的函数 */
function init() {

	initView();

	// 获取数据库连接句柄
	var odb = getOpenDatabase();

	// 如果获取的连接句柄为空或者不可用
	if (odb) {

		// 连接数据库，并获取数据库对象
		db = odb("feedbackDatabase", "1.0", "This is a feedback database!",
				10 * 1024 * 1024);
		db.transaction(function(t) {
			// db.transaction(fun)方法用来操作数据库,fun为操作数据库的回调方法
			// 该方法含有四个参数,分别是：
			// 1.sql： 待执行的sql语句，sql语句中的条件值用?来表示,如name=?
			// 2.params： 用来指明sql语句中的条件值，该参数为一个数组，可以把条件值按顺序放入,如["simple"]
			// 3.resultFun: 数据库操作执行成功后的回调方法
			// 4.errorFun： 数据库执行失败后的回调方法
			// 定义代执行的sql语句
			var createTableSql = "create table if not exists feedback ("
					+ "id Integer primary key autoincrement,"
					+ "MsgContent text not null," + "contact text not null," +"createTime text not null"+ ")";

			// t.executeSql(sql,params,resultFun,errorFun)方法用来操作数据库,
			// 该方法含有四个参数,分别是：
			// 1.sql： 待执行的sql语句，sql语句中的条件值用?来表示,如name=?
			// 2.params： 用来指明sql语句中的条件值，该参数为一个数组，可以把条件值按顺序放入,如["simple"]
			// 3.resultFun: 数据库操作执行成功后的回调方法
			// 4.errorFun： 数据库执行失败后的回调方法
			//t.executeSql('DROP TABLE feedback');
			t.executeSql(createTableSql, [], function(t, r) {
				//log("创建成功!");
			}, function(t, e) {
				//log("Create Table :" + e.message);
			});
		});
	} else {
		log("您的浏览器不支持本地数据库!");
	}
}

function savefeedback() {

	var MsgContent = _MsgContent.value;
	var contact = _contact.value;
	var myTime=CurentTime();
	$.ajax({
		type : "POST",
		url : "feedbackService",
		data : {
			"mes" : MsgContent,
			"contact" : contact,
			"myTime" : myTime
		},
		success : function(result) {
			if(result==0)
				log("提交成功!");
			else
				log("提交失败，请检查网络是否连接!");
		}
	});
	db.transaction(function(t) {
		var createTableSql = "insert into feedback values( null,'" + MsgContent
				+ "','" + contact + "','"+ myTime +"')";
		// 执行sql语句
		t.executeSql(createTableSql, [], function(t, r) {
			log("已保存到本地!");
		}, function(t, e) {
			log("Insert Table :" + e.message);
		});
	});
}

/*
function findById() {

	// 获取ID
	var id = input_id.value;
	log("ID: " + id);
	// 执行数据库操作，查询
	db.transaction(function(t) {
		var querySql = "select * from feedback where id = ?";
		t.executeSql(querySql, [ id ], function(t, r) {
			showData(r.rows);
		}, function(t, e) {
			log("Can't get the data");
		});
	});
}

function updatePeople() {
	// 获取ID
	var id = input_id.value;
	// 从页面中获取用户输入的人员姓名
	var MsgContent = _MsgContent.value;
	// 从页面中获取用户输入的人员年龄
	var contact = _contact.value;

	// 执行数据库操作
	db.transaction(function(t) {
				// 声明代执行的sql语句
				var createTableSql = "update feedback set MsgContent = ?, contact = ? where id = ?";
				// 执行sql语句
				t.executeSql(createTableSql, [ MsgContent, contact, id ], function(
						t, r) {
					log("更新数据成功!" );
				}, function(t, e) {
					log("Update feedback :" + e.message);
				});
			});
}
*/
function del(id) {

	// 执行数据库操作
	db.transaction(function(t) {
		// 声明代执行的sql语句
		var createTableSql = "delete from feedback where id = ?";
		// 执行sql语句
		t.executeSql(createTableSql, [ id ], function(t, r) {
			//log("删除数据成功! ID:" + id);
			findAll();
		}, function(t, e) {
			log("Delete feedback :" + e.message);
		});
	});
}

function findAll() {

	db.transaction(function(t) {
		var querySql = "select * from feedback";
		t.executeSql(querySql, [], function(t, r) {
			showData(r.rows);
		}, function(t, e) {
			log("Can't get the data");
		});
	});
}
function log(text) {
	$("#log").html(text).show(300).delay(2000).hide(300);
	//_log.innerHTML = text;
}
function CurentTime(){ 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
       
        var clock = year + "-";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day + " ";
       
        if(hh < 10)
            clock += "0";
           
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm; 
        return(clock); 
}
function showData(rows){
	
	var insertHtml = "<ul>";
	
	var len = rows.length;
	
	for ( var i = 0; i < len; i++) {
		
		var row = rows.item(i);

		insertHtml+="<a onclick='del("+row.id+")'><li ><span class='content'>";
		insertHtml+=row.MsgContent;
		insertHtml+="</span><span class='time'>";
		insertHtml+=row.createTime;
		insertHtml+="</span></li></a>";
		
	}
	
	insertHtml += "</ul>";
	
	$(".container").html(insertHtml);
	
}