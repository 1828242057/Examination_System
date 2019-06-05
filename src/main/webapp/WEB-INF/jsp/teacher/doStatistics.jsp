<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>课程统计</title>
<link rel="bookmark" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/Chart.min.js"></script>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-2">
								<br>
								<button type="button" class="btn btn-success active" onclick="returnlist()">返回</button>
							</div>
							<div style="margin-left:33%;margin-top:15px;margin-bottom:15px">
								<h1 style="display:inline">分值分段统计图</h1>
								<h3 style="display:inline;" >（${coursename}）</h3>
							</div>						
						</div>
					</div>
					
					<div style="height:470px;margin:30px 30px 70px 30px">
        				<canvas id="chartjs-combo-chart"></canvas>
    				</div>
    				<hr/><hr/>
    				
    				<div style="height:470px;margin:70px 30px 10px 30px">
        				<canvas id="chartjs-combo-chart2"></canvas>
    				</div>
				</div>
				
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(3)").addClass("active")
        function returnlist() {
        	//返回分析科目界面
        	window.location.href="${pageContext.request.contextPath}/teacher/courseStatistics";
    	}

		var d1=0;
    	var d2=0;
    	var d3=0;
    	var d4=0;
    	var d5=0;
    	var d6=0;
    	var d7=0;
    	var d8=0;
    	var d9=0;
    	var d10=0;
    	var d11=0;
    	var d12=0;
	    window.Factor = function () {
	    	var listjson = '${selectedCourseList}';
	    	listjson =JSON.parse(listjson);
	    	
	    	for(var item in listjson){
	    		if(listjson[item].mark>=0&&listjson[item].mark<10){
	    			d1++;
	    		}
	    		if(listjson[item].mark>=10&&listjson[item].mark<20){
	    			d2++;
	    		}
	    		if(listjson[item].mark>=20&&listjson[item].mark<30){
	    			d3++;
	    		}
	    		if(listjson[item].mark>=30&&listjson[item].mark<40){
	    			d4++;
	    		}
	    		if(listjson[item].mark>=40&&listjson[item].mark<50){
	    			d5++;
	    		}
	    		if(listjson[item].mark>=50&&listjson[item].mark<60){
	    			d6++;
	    		}
	    		if(listjson[item].mark>=60&&listjson[item].mark<70){
	    			d7++;
	    		}
	    		if(listjson[item].mark>=70&&listjson[item].mark<80){
	    			d8++;d11++;
	    		}
	    		if(listjson[item].mark>=80&&listjson[item].mark<90){
	    			d9++;if(listjson[item].mark<=84)d11++;else d12++;
	    		}
	    		if(listjson[item].mark>=90&&listjson[item].mark<=100){
	    			d10++;d12++;
	    		}
	    	}
	    };
	  	Factor();
	  	Chart.defaults.global.defaultFontSize = 18;
	    var comboConfig = {
	    	type: 'bar',
	            data: {
	                labels: ["0-9", "10-19", "20-29", "30-39", "40-49","50-59","60-69", "70-79", "80-89", "90-100"],
	                datasets: [{
	                    type: 'bar',
	                    label: '人数',
	                    data: [d1,d2,d3,d4,d5,d6,d7,d8,d9,d10],
	                	backgroundColor: [
	                		'rgba(255, 0, 0)',
	                		'rgba(255, 69, 0)',
	                		'rgba(255, 91, 71)',
	                		'rgba(255, 140, 0)',
	                		'rgba(255, 165, 0)',
	                		'rgba(255, 165, 79)',
	                		'rgba(200, 200, 50)',
	                		'rgba(173, 255, 47)',
	                		'rgba(127, 255, 212)',
	                		'rgba(70, 130, 180)'
	                  	]
	                }]
	            },
	            options: {
	                responsive: true,
	                maintainAspectRatio: false,
	                legend: {
	                    display: false
	                    },
	                title: {
	                    display: true,
	                    text: 'X(分段)    /    Y(人数)',
	                    fontSize:22,
	                    position: 'bottom'
	                },
	                tooltips: {
	                    mode: 'index',
	                    intersect: true
	                },
	                scales: {
	                	yAxes:[{
	                		ticks:{
	                			beginAtZero:true
	                		}
	                	}]
	                }
	                
	            }
	        };
	    var comboCtx = document.getElementById("chartjs-combo-chart").getContext("2d");
	    window.myCombo = new Chart(comboCtx, comboConfig);
	    
	    
	    var comboConfig2 = {
		    	type: 'pie',
		            data: {
		                labels: ["不及格(<60)", "及格(60~69)", "良好(70~84)", "优秀(>=85)"],
		                datasets: [{
		                    label: '# of Votes',
		                    data: [d1+d2+d3+d4+d5+d6,d7,d11,d12],
		                	backgroundColor: [
		                		'rgba(255, 0, 0)',
		                		'rgba(200, 200, 50)',
		                		'rgba(127, 255, 212)',
		                		'rgba(70, 130, 180)'
		                  	],
		                }]
		            },
		            options: {
		                responsive: true,
		                title: {
		                    display: true,
		                    text: '成绩等级分布图',
		                    fontSize:25,
		                    position: 'bottom'
		                },
		            }
		        };
	    
	    var comboCtx2 = document.getElementById("chartjs-combo-chart2").getContext("2d");
	    window.myCombo = new Chart(comboCtx2, comboConfig2);
	</script>
</html>