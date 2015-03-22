function getData(type,n,nowdate){
	$("#hscroller").width(320);
	if (!navigator.onLine){//判断如果是离线状态，取localStorage里的数据
		 var photo = window.localStorage.getItem("photo");
		 var music = window.localStorage.getItem("music");
		 var tao = window.localStorage.getItem("tao");
		 var sui = window.localStorage.getItem("sui");
		 if(type=="photo")
			 showPhotoData(photo);
		 if(type=="music")
			 showMusicData(music);
		 if(type=="tao")
			 showTaoData(tao);
		 if(type=="sui")
			 showSuiData(sui);
	}else{
		$.ajax({
			type : "POST",
			url : "getdataService",
			data : {
				"n" : n,
				"nowdate" : nowdate,
				"type" : type
			},
			success : function(result) {	
				if(type=="photo"){
					if (navigator.onLine)
					window.localStorage.setItem("photo",result);
					showPhotoData(result);
				}
				if(type=="music"){
					if (navigator.onLine)
					window.localStorage.setItem("music",result);
					showMusicData(result);
				}
				if(type=="tao"){
					if (navigator.onLine)
					window.localStorage.setItem("tao",result);
					showTaoData(result);
				}
				if(type=="sui"){
					if (navigator.onLine)
					window.localStorage.setItem("sui",result);
					showSuiData(result);			  
				}
			}
		});
	}
}

function getDate(){
	var nowDate = new Date();
	var year = nowDate.getFullYear();
	var month = nowDate.getMonth()+1;
	var day = nowDate.getDate();
	if(month<10)
		month="0"+month;
	if(day<10)
		day="0"+day;
	return year+"-"+month+"-"+day;
}
function getWeek(now){
	var dt = new Date(now); 
	var aryWeek=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
	var week=aryWeek[dt.getDay()]; 
	return week;
}
function getFormatData(now){
	var aryNow = now.split("-");
	return aryNow[0]+"/"+aryNow[1]+"/"+aryNow[2];
}
function iniMusic(musicsrc,i){
	if(i>0)
		musicsrc="";
	$("#jquery_jplayer_"+(i+1)).jPlayer({
	    ready: function () {
	      if(i==0)	
	      $('#tips1').html("请出现曲目时间后再进行播放…").show(300).delay(3000).hide(300);
	      $(this).jPlayer("setMedia", {
	    	  m4a:musicsrc
	      });
	    },
	    play: function() { // To avoid multiple jPlayers playing together.
	      $(this).jPlayer("pauseOthers");
	      if(navigator.userAgent.indexOf('Android') == -1)//判断是安卓设备，则不旋转
	      	$('.round_wrapper').eq(i).addClass('round_wrapper_Go');
	      $('#tips'+(i+1)).html("点击进度条下方的按钮可取消循环播放…").show(300).delay(3000).hide(300);
	    },
	    pause: function(){
	      if(navigator.userAgent.indexOf('Android') == -1)//判断是安卓设备，则不旋转
	      	$('.round_wrapper').eq(i).removeClass('round_wrapper_Go');
	    },
	    swfPath: "js",
	    supplied: "m4a,mp3",
	    //loop:true,//自动单曲循环
	    cssSelectorAncestor: "#jp_container_"+(i+1),
	    wmode: "window",
	    smoothPlayBar: true,
	    keyEnabled: true
	});
}
function showPhotoData(result){
	var data = eval('('+ result +')').data;//注意要加（）
	var sHtml="<ul>";
	for(var i=0;i<data.length;i++){
		sHtml+= "<li><div class=\"standard\" id=\"page"+(i+1)+"\"><div class=\"vscroller\"><div class=\"container\">";
		sHtml+= "<header><ul><li>V."+data[i].vol+"</li></ul></header>";
		if(i==0)
        	sHtml+= "<div class=\"main\"><img  src="+data[i].src+" data-original="+data[i].src+" >";
        else
        	sHtml+= "<div class=\"main\"><img  src=\"img/sys/loading.gif\" data-original="+data[i].src+">";
        sHtml+= "<div class=\"etc\">"+data[i].name+"<hr/>by "+data[i].author+"</div></div>";                
        sHtml+= "<div class=\"sub\"><div class=\"date\"><h4>"+getWeek(data[i].time)+"</h4>";  
        sHtml+= "<div class=\"YMD\">"+getFormatData(data[i].time) +"</div></div>";              
        sHtml+= "<div class=\"quotation\">"+data[i].quotation+"</div></div>";             
        sHtml+= "</div></div></div></li>";
	}
	sHtml+="</ul>";
	$("#hscroller").html(sHtml);
}
function showMusicData(result){
	var data = eval('('+ result +')').data;//注意要加（）
	var sHtml="<ul>";
	for(var i=0;i<data.length;i++){
		sHtml+= "<li><div class=\"standard\" id=\"page"+(i+1)+"\"><div class=\"vscroller\"><div class=\"container\">";
		if(i==0)
			sHtml+= "<div class=\"round_bg\"></div><div class=\"round_wrapper\"><img  src="+data[i].coversrc+" data-original="+data[i].coversrc+"></div>";
		else  
			sHtml+= "<div class=\"round_bg\"></div><div class=\"round_wrapper\"><img  src=\"img/sys/loadinground.gif\" data-original="+data[i].coversrc+"></div>";
        sHtml+= "<div id=\"jquery_jplayer_"+(i+1)+"\" class=\"jp-jplayer\"></div><div id=\"jp_container_"+(i+1)+"\" class=\"jp-audio\">";
        sHtml+= "<div class=\"jp-type-single\"><div class=\"jp-gui jp-interface\"><ul class=\"jp-controls\">";  
        sHtml+= "<li><a href=\"javascript:;\" class=\"jp-play\" tabindex=\"1\">play</a></li>";  
        sHtml+= "<li><a href=\"javascript:;\" class=\"jp-pause\" tabindex=\"1\">pause</a></li>";   
        sHtml+= "<li><a href=\"javascript:;\" class=\"jp-stop\" tabindex=\"1\">stop</a></li>";  
        sHtml+= "</ul><div class=\"jp-progress\"><div class=\"jp-seek-bar\"><div class=\"jp-play-bar\"></div></div></div>";  
        sHtml+= "<div class=\"jp-volume-bar\"><div class=\"jp-volume-bar-value\"></div></div>";  
        sHtml+= "<div class=\"jp-time-holder\"><div class=\"jp-current-time\"></div><div class=\"jp-duration\"></div>";  
        sHtml+= "<ul class=\"jp-toggles\"><li><a href=\"javascript:;\" class=\"jp-repeat\" tabindex=\"1\" title=\"repeat\">repeat</a></li>";  
        sHtml+= "<li><a href=\"javascript:;\" class=\"jp-repeat-off\" tabindex=\"1\" title=\"repeat off\">repeat off</a></li></ul></div></div>";  
        sHtml+= "<div class=\"jp-no-solution\"><span>Update Required</span>"; 
        sHtml+= " To play the media you will need to either update your browser to a recent version or update your <a href=\"http://get.adobe.com/flashplayer/\" target=\"_blank\">Flash plugin</a>."; 
        sHtml+= "</div></div></div>";             
        sHtml+= "<div class=\"track-details\">"+data[i].name+" <em>by</em> "+data[i].author+"</div>";                
        sHtml+= "<div id=\"tips"+(i+1)+"\" class=\"tips\"></div>";     
        sHtml+= "<input value=\""+data[i].musicsrc+"\" id=\"music"+(i+1)+"\" type=\"hidden\" />";        
        sHtml+= "</div></div></div></li>";
	}
	sHtml+="</ul>";
	$("#hscroller").html(sHtml);
	for(var i=0;i<data.length;i++){
	  var musicsrc = data[i].musicsrc;
	  iniMusic(musicsrc,i); 
	}
}
function showTaoData(result){
	var data = eval('('+ result +')').data;//注意要加（）
	var sHtml="<ul>";
	for(var i=0;i<data.length;i++){
		sHtml+= "<li><div class=\"standard\" id=\"page"+(i+1)+"\"><div class=\"vscroller\"><div class=\"container\">";
		if(i==0) 
			sHtml+= "<a onclick=\"openUrl('"+data[i].websrc+"')\"><img  src="+data[i].coversrc+" data-original="+data[i].coversrc+"></a>"; 
		else
			sHtml+= "<a onclick=\"openUrl('"+data[i].websrc+"')\"><img  src=\"img/sys/loading.gif\" data-original="+data[i].coversrc+"></a>"; 
		sHtml+= "<div class=\"etc\">"+data[i].etc+"</div>";                          
        sHtml+= "</div></div></div></li>";
	}
	sHtml+="</ul>";
	$("#hscroller").html(sHtml);
}
function showSuiData(result){
	var data = eval('('+ result +')').data;//注意要加（）
	var sHtml="<ul>";
	for(var i=0;i<data.length;i++){
		sHtml+= "<li><div class=\"standard\" id=\"page"+(i+1)+"\"><div class=\"vscroller\"><div class=\"container\">";
		sHtml+= "<div class=\"app_info\"><img class=\"app_img\" src="+data[i].typesrc+">";
		sHtml+= "<div class=\"app_txt\"><h3>"+data[i].title+"</h3></div><a class=\"app_btn\" >"+data[i].type+"</a></div>"; 
		if(data[i].coversrc=="#")
			sHtml+= "<div id=\"from\"><a onclick=\"openUrl('"+data[i].websrc+"')\"><h3>From [知乎] </h3></a></div>";
		else{
			if(i==0) 
				sHtml+= "<a onclick=\"openUrl('"+data[i].websrc+"')\"><img id=\"cover"+(i+1)+"\" src=\""+data[i].coversrc+"\" data-original=\""+data[i].coversrc+"\"></a>";
			else
				sHtml+= "<a onclick=\"openUrl('"+data[i].websrc+"')\"><img id=\"cover"+(i+1)+"\" src=\"img/sys/loading.gif\" data-original=\""+data[i].coversrc+"\"></a>";
		}
			
		sHtml+= "<div class=\"etc\">"+data[i].etc+"</div>";                          
        sHtml+= "</div></div></div></li>";
	}
	sHtml+="</ul>";
	$("#hscroller").html(sHtml);
}