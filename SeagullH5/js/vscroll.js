	  /*初始垂直化滚动条*/
      var myScroll;
      function loaded() {
        myScroll = new iScroll('wrapper',{
          bounce:false,
          hideScrollbar: true,
          fadeScrollbar: true
        });
      }
      document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
      document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
