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
							<h1 style="text-align: center;">单科成绩分析</h1>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered">
						<thead>
							<tr>
								<th>课程号</th>
								<th>课程名称</th>
								<th>上课时间</th>
								<th>上课地点</th>
								<th>周数</th>
								<th>课程类型</th>
								<th>学分</th>
								<th>考试成绩</th>
								<th>作业成绩</th>
								<th>出勤成绩</th>
								<th>实验成绩</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${courseList}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<td>${item.coursetime}</td>
									<td>${item.classroom}</td>
									<td>${item.courseweek}</td>
									<td>${item.coursetype}</td>
									<td>${item.score}</td>
									<td>${item.boardscores}</td>
									<td>${item.homeworkscores}</td>
									<td>${item.attendancescores}</td>
									<td>${item.experimentalscores}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info"
											onClick="location.href='${pageContext.request.contextPath}/teacher/doStatistics?id=${item.courseid}'">分析</button>
										<!--弹出框显示分值分段图-->
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>

				</div>

			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script>
    $("#nav li:nth-child(3)").addClass("active")
    
</script>
</html>