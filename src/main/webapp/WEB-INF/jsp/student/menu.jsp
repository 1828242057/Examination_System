<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="col-md-2">
	<div>
		<ul class="nav nav-pills nav-stacked" id="nav">
			<li><a
				href="${pageContext.request.contextPath}/student/showCourse">所有课程<span
					class="badge pull-right"></span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/student/selectedCourse">已选课程<span
					class="badge pull-right"></span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/student/overCourse">已修课程<span
					class="badge pull-right"></span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/student/passwordRest">修改密码<span
					class="glyphicon glyphicon-pencil pull-right"></span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/student/showResponsive">反馈信息<span
					class="glyphicon glyphicon-comment pull-right"></span></a></li>
			<li><a href="${pageContext.request.contextPath}/logout">退出系统<span
					class="glyphicon glyphicon-log-out pull-right"></span></a></li>
		</ul>
	</div>
</div>