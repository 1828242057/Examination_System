<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<link rel="bookmark" type="image/x-icon" href="images/favicon.ico"/>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="icon" href="images/favicon.ico">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background:url("images/background2.png") no-repeat;
	background-size:100% 100%;
	background-attachment:fixed;
}

#login-box {
	padding: 35px;
	margin-top: 70px;
	border-radius: 15px;
	background: rgb(131, 131, 131);
	color: #fff;
}
</style>
</head>
<body>
	<div class="container" id="top">
		<div class="row" style="margin-top: 150px;"></div>
		<img alt="logo" src="images/logo.png" height=100px width=450px style="display:block;margin-left:auto;margin-right:auto;"/>
		<div class="row")>
			<!--<div class="col-md-7">
				<img alt="xm" height=350px width=650px src="images/a.png"
					class="img-rounded" />
			</div>-->
			<div class="col-md-5" id="login-box" style="margin-left:30%;width:450px;padding-bottom:20px">
				<br>
				<form class="form-horizontal" role="form"
					action="${pageContext.request.contextPath}/login" id="from1"
					method="post">
					<div class="form-group">
						<span class="glyphicon glyphicon-user col-sm-3"
							style="color: rgb(255, 140, 60); font-size: 20px; width:110px"> 用户</span>
						<div class="col-sm-9" style="width:280px">
							<input type="text" class="form-control" id="userID"
								placeholder="请输入用户名" name="username">
						</div>
					</div>
					<div class="form-group">
						<span class="glyphicon glyphicon-lock col-sm-3"
							style="color: rgb(255, 140, 60); font-size: 20px;width:110px"> 密码</span>
						<div class="col-sm-9" style="width:280px">
							<input type="password" class="form-control" id="password"
								placeholder="请输入密码" name="password">
						</div>
					</div>
					<div class="form-group" style="margin-left:120px;margin-top:30px">
						<button type="submit" class="btn btn-default btn-info col-sm-6">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="include/Footer.jsp"%>
</body>
<%--<div class="form-group">--%>
<%--<div class="col-sm-offset-2 col-sm-10">--%>
<%--<div class="checkbox">--%>
<%--<label class="checkbox-inline">--%>
<%--<input type="radio" name="role" value="1" checked>管理员--%>
<%--</label>--%>
<%--<label class="checkbox-inline">--%>
<%--<input type="radio" name="role" value="2">老师--%>
<%--</label>--%>
<%--<label class="checkbox-inline">--%>
<%--<input type="radio" name="role" value="3">学生--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
</html>