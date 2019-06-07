<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>查看成绩</title>
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
							<h1 class="col-md-7">${type}申请审核<font size="5">（${coursename}）</font></h1>
							<div class="btn-group" style="margin: 25px 40px 10px 0;float:right;">
									<button class="btn btn-default">选课/退选</button>
									<button data-toggle="dropdown" class="btn dropdown-toggle">
										<span class="caret" ></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/teacher/passedJoin?courseid=${courseid}">选课</a></li>
										<li><a href="${pageContext.request.contextPath}/teacher/passedQuit?courseid=${courseid}">退选</a></li>
									</ul>
							</div>
						</div>
					</div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="text-align:center;">学号</th>
								<th style="text-align:center;">姓名</th>
								<th style="text-align:center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${passedList}" var="item">
								<tr>
									<td style="text-align:center;">${item.studentCustom.userid}</td>
									<td style="text-align:center;">${item.studentCustom.username}</td>
									<td style="text-align:center;">
									<c:choose>
										<c:when test="${type eq '选课'}">
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='${pageContext.request.contextPath}/teacher/passedYes?id=${item.id}&type=0'">同意</button>
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='${pageContext.request.contextPath}/teacher/passedNo?id=${item.id}&type=0'">拒绝</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='${pageContext.request.contextPath}/teacher/passedYes?id=${item.id}&type=1'">同意</button>
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='${pageContext.request.contextPath}/teacher/passedNo?id=${item.id}&type=1'">拒绝</button>
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a
										href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a
										href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
        <c:if test="${pagingVO != null}">
        if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
            $(".pagination li:last-child").addClass("disabled")
        };

        if (${pagingVO.curentPageNo} == ${1}) {
            $(".pagination li:nth-child(1)").addClass("disabled")
        };
        </c:if>
	</script>
</html>