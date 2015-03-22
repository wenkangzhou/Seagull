function share(title,src){
    if(window.myAndroidJs==undefined){
        /*
        window.location.href="http://v.t.sina.com.cn/share/share.php?appkey=&url="+window.location.href+"&content=gb2312&retcode=0&title="+quotation+"&ralateUid=1867364882"+"&pic="+window.location.href+"/"+photoSrc+"";
        */
        /*分享到新浪微博*/
        title=title+" FROM [海鸥]";
        window.open("http://v.t.sina.com.cn/share/share.php?appkey=2450960581&type=1&url="+window.location.href+"&content=gb2312&title="+title+"&ralateUid=1867364882&pic=http://www.lazyseagull.com/"+src+"","_blank");
        /*分享到豆瓣
        window.location.href="javascript:void(function(){var d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('"+quotation+"')+'&sel='+e(s)+'&v=1',x=function(){if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=450,height=330'))location.href=r+'&r=1'};if(/Firefox/.test(navigator.userAgent)){setTimeout(x,0)}else{x()}})()";
        */
    }
    else{
        var status = window.myAndroidJs.getNetStatus();
        if(status==0)
        	window.myAndroidJs.onClickShare(window.location.href,title);
    }  
}