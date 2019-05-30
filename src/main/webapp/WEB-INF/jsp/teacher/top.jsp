<%--
  Created by IntelliJ IDEA.
  User: Jacey
  Date: 2017/6/30
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--shiro标签--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- 顶栏 -->
<div class="container" id="top">
	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"> <img alt="Brand"
						style="max-width: 200px; margin-top: -7px;"
						src="${pageContext.request.contextPath}/images/logo.png">
					</a>
				</div>
				<div class="navbar-brand" style="float:right;">
					<span class="glyphicon glyphicon-user"><shiro:principal /></span>
					<span> <script type="text/javascript">
                     		var date = new Date();
                     		document.write("，老师您好！");
                   		</script>
					</span>
					<span> <script type="text/javascript">
                     		var date = new Date();
                     		document.write(date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日" + " 星期" + "日一二三四五六".charAt(date.getDay()));
                   		</script>
					</span>
				</div>
			</div>

		</div>
	</div>
</div>
