<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>发布考试信息</title>
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

<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

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
							<h1 class="col-md-5">发布考试信息</h1>
						</div>
					</div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>课程号</th>
								<th>课程名称</th>
								<th>考试时间地点</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${courseList}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<form action="${pageContext.request.contextPath}/teacher/postTestInformation" method="post">
										<input readonly="readonly" type="hidden" class="form-control"
											name="courseid" value="${item.courseid}">
										<td style="padding:0;"><input type="text" style="margin:6px 0 0 6px;
										width:90%" name="examinationplan" placeholder="请输入考试信息" value="${item.examinationplan}">
										</td>
										<td><button class="btn btn-default btn-xs btn-info" type="submit">发布</button></td>
									</form>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	
	<div class="container" id="footer">
	
		<div class ="row">
			<div class="col-md-12"></div>
		</div>
	</div>
	
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
		$("#nav li:nth-child(1)").addClass("active")
</script>
</html>