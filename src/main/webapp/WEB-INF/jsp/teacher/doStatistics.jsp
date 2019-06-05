<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title></title>

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
							<h1 class="col-md-5">该科分值分段统计图</h1>						
						</div>
					</div>
					
					<div style="height:400px;width:80%;">
        				<canvas id="chartjs-combo-chart"></canvas>
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
		window.chartColors = {
	            red: 'rgb(255, 99, 132)',
	            orange: 'rgb(255, 159, 64)',
	            yellow: 'rgb(255, 205, 86)',
	            green: 'rgb(75, 192, 192)',
	            blue: 'rgb(54, 162, 235)',
	            purple: 'rgb(153, 102, 255)',
	            grey: 'rgb(201, 203, 207)'
	    };

		var d1=0;
    	var d2=0;
    	var d3=0;
    	var d4=0;
    	var d5=0;
	    window.Factor = function () {
	    	var listjson = '${selectedCourseList}';
	    	listjson =JSON.parse(listjson);
	    	d1=0;
	    	d2=0;
	    	d3=0;
	    	d4=0;
	    	d5=0;
	    	
	    	for(var item in listjson){
	    		if(listjson[item].mark>=0&&listjson[item].mark<=20){
	    			d1++;
	    		}
	    		if(listjson[item].mark>20&&listjson[item].mark<=40){
	    			d2++;
	    		}
	    		if(listjson[item].mark>40&&listjson[item].mark<=60){
	    			d3++;
	    		}
	    		if(listjson[item].mark>60&&listjson[item].mark<=80){
	    			d4++;
	    		}
	    		if(listjson[item].mark>80&&listjson[item].mark<=100){
	    			d5++;
	    		}
	    	}
	    };
	  	Factor();
	    var comboConfig = {
	    	type: 'bar',
	            data: {
	                labels: ["0-20", "20-40", "40-60", "60-80", "80-100",],
	                datasets: [{
	                    type: 'bar',
	                    label: '人数',
	                    backgroundColor: window.chartColors.blue,
	                    data: [
	                        d1,//10,
	                        d2,//5,
	                        d3,//10,
	                        d4,//40,
	                        d5//20
	                    ]
	                }]
	            },
	            options: {
	                responsive: true,
	                maintainAspectRatio: false,
	                title: {
	                    display: true,
	                    text: '分值分段统计图'
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
	</script>
</html>