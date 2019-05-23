<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="${pageContext.request.contextPath}/js/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="${pageContext.request.contextPath}/js/bootstrap-table-zh-CN.min.js"></script>
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
							<h1 class="col-md-5">反馈信息</h1>
						</div>
					</div>
					<table data-toggle="table">
						<thead>
							<tr>
								<th data-align="center">发送者</th>
								<th data-align="center">相关课程</th>
								<th data-align="center">内容</th>
								<th data-align="center">时间</th>
								<th data-align="center">处理状态</th>
								<th data-align="center">操作</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>132</td>
									<td>132</td>
									<td>123</td>
									<td>123</td>
									<td>0</td>
									<td><a 
										href="${pageContext.request.contextPath}/teacher/showFeedtext">
										<span class="glyphicon glyphicon-check">查看</span></a></td>
								</tr>
								<tr>
									<td>132</td>
									<td>132</td>
									<td>123</td>
									<td>123</td>
									<td>0</td>
									<td><a 
										href="${pageContext.request.contextPath}/teacher/showFeedtext">
										<span class="glyphicon glyphicon-check">查看</span></a></td>
								</tr>
								<tr>
									<td>132</td>
									<td>132</td>
									<td>123</td>
									<td>123</td>
									<td>0</td>
									<td><a 
										href="${pageContext.request.contextPath}/teacher/showFeedtext">
										<span class="glyphicon glyphicon-check">查看</span></a></td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(2)").addClass("active")
        
	</script>
</html>