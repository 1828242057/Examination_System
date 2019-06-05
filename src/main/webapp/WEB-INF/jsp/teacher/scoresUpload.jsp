<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>上传成绩</title>
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
							<div class="col-sm-1"></div>
							<button type="button" class="btn btn-success active" onclick="returnlist()">返回</button>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="col-sm-1"></div>
					<form id="uploadForm" action="${pageContext.request.contextPath}/scoresUpload" method="post" enctype="multipart/form-data">
						<input type="file" name="file"/>
						<input readonly="readonly" type="hidden" class="form-control"
										name="courseid" value="${courseid}">
						<br/>
						<div class="col-sm-1"></div>
						<button type="submit">确认导入</button>
					</form>
					
					<br/>
					<c:if test="${not empty fileName}">
						<div class="col-sm-1"></div>
						<p style="color:green;">文件 " ${fileName} " 的导入结果为：</p>
					</c:if>
					<c:if test="${not empty successMessage}">
						<div class="col-sm-1"></div>
						<p style="color:green;">${successMessage}</p>
					</c:if>
					<c:if test="${not empty failMessage}">
						<div class="col-sm-1"></div>
						<p style="color:red;">${failMessage}</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
$("#nav li:nth-child(1)").addClass("active")
function returnlist() {
	//返回课程列表界面
	window.location.href="${pageContext.request.contextPath}/teacher/showCourse";
}
</script>
</html>