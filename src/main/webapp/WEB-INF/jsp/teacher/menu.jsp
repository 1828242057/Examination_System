<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="col-md-2">
	<ul class="nav nav-pills nav-stacked" id="nav">
		<li><a
			href="${pageContext.request.contextPath}/teacher/showCourse">我的课程</a></li>
		<li><a
			href="${pageContext.request.contextPath}/teacher/showResponsive">反馈信息<span
					class="glyphicon glyphicon-comment pull-right"></span></a></li>
		<li><a
			href="${pageContext.request.contextPath}/teacher/courseStatistics">统计分析<span
					class="glyphicon glyphicon-stats pull-right"></span></a></li>
		<li><a
			href="${pageContext.request.contextPath}/teacher/passwordRest">修改密码<span
					class="glyphicon glyphicon-pencil pull-right"></span></a></li>
		<li><a 
			href="${pageContext.request.contextPath}/logout">退出系统<span
					class="glyphicon glyphicon-log-out pull-right"></span></a></li>
	</ul>
</div>