/**  ÐÂÓÃ»§Í³¼ÆÍ¼  **/
function newUser() {
	$.ajax({
		async : false,
		type : "post",
		url : "log.do",
		data : {
			"method" : "findNewUser",
		},
		beforeSend:function(){
              $("#loading").html("<img src='resources/images/noti-loading.gif'/>");
              $("#loading").css("display","");
              $("#main").css("display","none");
         },
		success : function(result) {
			var result = eval('(' + result + ')');
			var chart;
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container1',
					type : 'line',
					marginRight : 130,
					marginBottom : 25
				},
				title : {
					text : '',
					x : -20
				},
				xAxis : {
					categories : result.date,
					labels : {
						rotation : -45, //×ø±êÖµÏÔÊ¾µÄÇãÐ±¶È
						formatter : function() {
							return this.value;
						}
					}
				},
				yAxis : {
					title : {
						text : 'ÓÃ»§Êý'
					},
					allowDecimals : false,
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' +
						this.x + ' ºÅ ' + this.y + 'ÈË';
					}
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'top',
					x : -10,
					y : 100,
					borderWidth : 0
				},
				series : [ {
					name : 'ÐÂÓÃ»§Êý',
					data : result.newUserCount
				} ]
			});
		},
		 complete:function(){
			$("#loading").css("display","none");
            $("#main").css("display","");
          }
	});
}

/** ÀÛ¼ÆÓÃ»§Í³¼ÆÍ¼ **/
function accumulationUser() {
	$.ajax({
		async : false,
		type : "post",
		url : "log.do",
		data : {
			"method" : "findAccumulationUserData",
		},
		beforeSend:function(){
            $("#loading").html("<img src='resources/images/noti-loading.gif'/>");
            $("#loading").css("display","");
            $("#main").css("display","none");
       },
		success : function(result) {
			var result = eval('(' + result + ')');
			var chart;
			chart = new Highcharts.Chart(
					{
						chart : {
							renderTo : 'container2',
							type : 'column',
							margin : [ 50, 50, 100, 80 ]
						},
						title : {
							text : '×î½ü30ÌìÀÛ¼ÆÓÃ»§Í³¼Æ'
						},
						xAxis : {
							categories : result.date,
							labels : {
								rotation : -45,
								align : 'right',
								style : {
									fontSize : '13px',
									fontFamily : 'Verdana, sans-serif'
								}
							}
						},
						yAxis : {
							min : 0,
							title : {
								text : 'ÓÃ»§Êý'
							}
						},
						legend : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return '<b>'
										+ this.x
										+ '</b><br/>'
										+ ' ºÅ'
										+ Highcharts
												.numberFormat(this.y, 0)
										+ ' ÈË';
							}
						},
						series : [ {
							name : 'Population',
							data : result.accumulationUserCount
						} ]
					});
		},
		 complete:function(){
				$("#loading").css("display","none");
	            $("#main").css("display","");
	          }
	});
}

/**  »îÔ¾ÓÃ»§Í³¼ÆÍ¼  **/
function activeUser() {
	$.ajax({
		async : false,
		type : "post",
		url : "log.do",
		data : {
			"method" : "findActiveUserData",
		},
		beforeSend:function(){
            $("#loading").html("<img src='resources/images/noti-loading.gif'/>");
            $("#loading").css("display","");
            $("#main").css("display","none");
       },
		success : function(result) {
			var result = eval('(' + result + ')');
			var chart;
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container3',
					type : 'line',
					marginRight : 130,
					marginBottom : 25
				},
				title : {
					text : '',
					x : -20
				},
				xAxis : {
					categories : result.date,
					labels : {
						rotation : -45, //×ø±êÖµÏÔÊ¾µÄÇãÐ±¶È
						formatter : function() {
							return this.value;
						}
					}

				},
				yAxis : {
					title : {
						text : '»îÔ¾ÓÃ»§Êý'
					},
					allowDecimals : false,
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' +
						this.x + ' ºÅ ' + this.y + 'ÈË';
					}
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'top',
					x : -10,
					y : 100,
					borderWidth : 0
				},
				series : [ {
					name : '»îÔ¾ÓÃ»§Êý',
					data : result.activeCount
				} ]
			});
		},
		 complete:function(){
				$("#loading").css("display","none");
	            $("#main").css("display","");
	          }
	});
}
/**  Æô¶¯´ÎÊýÇ÷ÊÆÍ¼   **/
function userStartCount() {
	$.ajax({
		async : false,
		type : "post",
		url : "log.do",
		data : {
			"method" : "findStartUserData"
		},
		beforeSend:function(){
            $("#loading").html("<img src='resources/images/noti-loading.gif'/>");
            $("#loading").css("display","");
            $("#main").css("display","none");
       },
		success : function(result) {
			var result = eval('(' + result + ')');
			var chart;
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container4',
					type : 'line',
					marginRight : 130,
					marginBottom : 25
				},
				title : {
					text : '',
					x : -20
				},
				xAxis : {
					categories : result.date,
					labels : {
						rotation : -45, //×ø±êÖµÏÔÊ¾µÄÇãÐ±¶È
						formatter : function() {
							return this.value;
						}
					}
				},
				yAxis : {
					title : {
						text : 'Æô¶¯´ÎÊý'
					},
					allowDecimals : false,
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' +
						this.x + ' ºÅ ' + this.y + '´Î';
					}
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'top',
					x : -10,
					y : 100,
					borderWidth : 0
				},
				series : [ {
					name : 'Æô¶¯´ÎÊý',
					data : result.startCount
				} ]
			});
		},
		 complete:function(){
				$("#loading").css("display","none");
	            $("#main").css("display","");
	          }
	});
}
