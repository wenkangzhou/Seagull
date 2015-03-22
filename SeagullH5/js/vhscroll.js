var scroll1,scroll2,scroll3,scroll4,scroll5,scroll6,scroll7,myScroll;
function loaded() {
  /*关键：根据正文高度自适应*/
  $("#wrapper").css("height",document.body.scrollHeight-100);
  $(".standard").css("height",document.body.scrollHeight-100);
  /*初始水平化滚动条*/
  myScroll = new iScroll('wrapper', {
      snap: true,
      momentum: false,
      hScrollbar: false,
      onScrollMove: function () {
        /*滑动中*/
      },
      onScrollEnd: function () {
        /*滑动完成*/
        var nowStyle=$("#hscroller").css("-webkit-transform");
        var nowPosition=nowStyle.substring(nowStyle.indexOf("(")+1,nowStyle.indexOf("px"));
        var nowPage=Math.abs(nowPosition)/320;
        if(document.title=="PHOTO"||document.title=="MUSIC"||document.title=="TAO"){
          var photoSrc=$("#page"+(nowPage+1) +" img").attr("src");
          var tempSrc=$("#page"+(nowPage+1) +" img").attr("data-original");
          if(tempSrc!=photoSrc)
            $("#page"+(nowPage+1) +" img").attr("src",tempSrc);
        }
        if(document.title=="SUI"){
          if($("#cover"+(nowPage+1)).attr("src")!=undefined){
            var photoSrc=$("#cover"+(nowPage+1)).attr("src");
            var tempSrc=$("#cover"+(nowPage+1)).attr("data-original");
            if(tempSrc!=photoSrc)
              $("#cover"+(nowPage+1)).attr("src",tempSrc);
          }
        }
        if(document.title=="MUSIC"){
          var musicsrc=$("#music"+(nowPage+1)).val();
          var nowsrc=$("#jp_audio_"+nowPage).attr("src");
          if(nowsrc==undefined){
            $("#jquery_jplayer_"+(nowPage+1)).jPlayer("setMedia", {
                m4a:musicsrc
            });
            $('#tips'+(nowPage+1)).html("待出现曲目时间后再进行播放…").show(300).delay(3000).hide(300);
          }
        }
      }
   });
  /*初始垂直化滚动条*/
  scroll1 = new iScroll('page1',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll2 = new iScroll('page2',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll3 = new iScroll('page3',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll4 = new iScroll('page4',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll5 = new iScroll('page5',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll6 = new iScroll('page6',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
  scroll7 = new iScroll('page7',{
    bounce:false,
    hideScrollbar: true,
    fadeScrollbar: true
  });
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
