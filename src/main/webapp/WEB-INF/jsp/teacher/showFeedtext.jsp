<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>反馈处理</title>
<link rel="bookmark" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">

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
							<div class="col-sm-1"></div>
							<button type="button" class="btn btn-success active" onclick="returnlist()">返回</button>
						</div>
					</div>
					<div class="panel-body">
						<form name="dealFeed" class="form-horizontal" role="form"
							action="${pageContext.request.contextPath}/teacher/showFeedtext" 
							id="viewfrom" method="post"> <!-- 处理完成后要修改状态值 -->
							<div class="form-group">
								<div class="col-sm-10">
									<input readonly="readonly" type="hidden" class="form-control"
										name="id" id="inputEmail3"
										value="${feedback.id}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">反馈内容:</label>
								<div class="col-sm-10">
									<textarea id="ct" rows="10" name="feedbacktext" cols="80" readonly style="color:gray;">
									</textarea>
								</div>
								<script type="text/javascript">
								//要实现内容为反馈表中的内容  cscs替代
									$("#ct").val("${feedback.feedbacktext}");  
								</script>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">处理结果:</label>
								<div class="col-sm-10">
									<c:choose>
										<c:when test="${feedback.processed ==false}">
											<textarea id="processResult" name="processtext" rows="10" cols="80" >
											</textarea>
										</c:when>
										<c:otherwise>
											<textarea id="processResult" name="processtext" rows="10" cols="80" readonly style="color:gray;">
											</textarea>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<script type="text/javascript">
								//要实现内容为反馈表中的内容  cscs替代
									$("#processResult").val("${feedback.processtext}");  
							</script>
							<c:if test="${feedback.processed ==false}">
								<div class="form-group" style="text-align: center">
									<button class="btn btn-default" type="submit">处理完成</button>
								</div>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
	<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(2)").addClass("active")
        function returnlist() {
        	//返回反馈列表界面
        	window.location.href="${pageContext.request.contextPath}/teacher/showResponsive";
    	}
	</script>
</html>