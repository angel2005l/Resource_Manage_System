<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="login-bg">

<head>
<title>新海集团_陪护宁信息管理后台_登录</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- this page specific styles -->
<link rel="stylesheet" href="<%=basePath%>css/compiled/signin.css"
	type="text/css" media="screen" />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<!-- background switcher -->
	<div class="row-fluid login-wrapper">
		<img class="logo" src="<%=basePath%>img/logo_simple.png" />
		<div class="span4 box">
			<div class="content-wrap">
				<img class="logo" src="<%=basePath%>img/bgs/xh_behome_login.png"
					style="margin-bottom: 20px;" /> <input class="span12" type="text"
					id="userName" placeholder="请输入账号" /> <input class="span12"
					type="password" id="password" placeholder="请输入密码" />
				<!--<a href="#" class="forgot">Forgot password?</a>-->
				<!--<div class="remember">
						<input id="remember-me" type="checkbox" />
						<label for="remember-me">Remember me</label>
					</div>-->
				<a class="btn-glow primary login" onclick="login()">登录</a>
			</div>
		</div>
		<!--<div class="span4 no-account">
				<p>Don't have an account?</p>
				<a href="signup.html">Sign up</a>
			</div>-->
	</div>

	<!-- pre load bg imgs -->
	<script type="text/javascript">
		$(function() {
			$("html").css("background-image", "url('<%=basePath%>img/bgs/bgimage.jpg')");
		});
		function login() {
		$.ajax({
			url:'<%=basePath%>userManage?method=user_login',
			type:'post',
			data:{"user_name":$("#userName").val(),"passwd":$("#password").val()},
			dataType:"json",
			success:function(result){
				if(result.code == 0){
					window.location.href="<%=basePath%>view/index.jsp";
					} else {
						alert(result.msg);
						location.reload();
					}
				},
				error : function() {
					alert("服务未响应")
				}

			})
		}
	</script>
</body>

</html>