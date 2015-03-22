      function openUrl(url){
        //判断是否在客户端访问
        if(window.myAndroidJs==undefined){
        	if (navigator.onLine)
        		window.open(url,'_blank');
        }
        else{
        	var status = window.myAndroidJs.getNetStatus();
        	if(status==0)//判断网络是否连接
        		window.myAndroidJs.openBrowser(url);
        }
      }  
      