<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>反馈</title>
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
							<h1 style="text-align: center;">新建反馈</h1>
						</div>
						<button type="button" class="btn btn-success active" onclick="returnlist()" 
						style="margin-top:25px;margin-right:20px;float:right">返回</button>
					</div>
					<div class="panel-body">
						<form name="reset" class="form-horizontal" role="form"
							action="${pageContext.request.contextPath}/student/Responsive"
							id="editfrom" method="post" onsubmit="return check()">
							<div class="form-group">
								<label class="col-sm-2 control-label">请选择课程</label>
								<div class="col-sm-5">
									<select class="form-control" name="courseid">
										<c:forEach items="${selectedCourseCustomList}" var="item">
											<option value="${item.couseCustom.courseid}">${item.couseCustom.coursename}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">反馈内容</label>
								<div class="col-sm-10">
									<textarea name="feedbacktext" class="form-control"
										style="height: 200px" placeholder="请输入内容"></textarea>
								</div>
							</div>
							<div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
	$("#nav li:nth-child(5)").addClass("active")
	function returnlist() {
        	//返回反馈列表界面
        	window.location.href="${pageContext.request.contextPath}/student/showResponsive";
    	}
</script>
</html>