<%@ include file="base/base.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>新海集团_陪护宁数据管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- libraries -->
		<link href="<%=basePath %>css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />

		<!-- this page specific styles -->
		<link rel="stylesheet" href="<%=basePath %>css/compiled/index.css" type="text/css" media="screen" />
	</head>

	<body>

		<!-- navbar -->
		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<a class="brand" href="javascript:void(0)" ><i class="icon-fire"></i>&nbsp;&nbsp;&nbsp;<img src="<%=basePath %>img/logo_simple.png" /></a>
				<ul class="nav pull-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">
							账户信息
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="personal-info.html">Personal info</a>
							</li>
							<li>
								<a href="#">Account settings</a>
							</li>
							<li>
								<a href="#">Billing</a>
							</li>
							<li>
								<a href="#">Export your data</a>
							</li>
							<li>
								<a href="#">Send feedback</a>
							</li>
						</ul>
					</li>
					<li class="settings hidden-phone">
						<a href="login.jsp" role="button">
							<i class="icon-share-alt"></i>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- end navbar -->

		<!--左侧导航栏-->
		<!-- sidebar -->
		<div id="sidebar-nav">
			<ul id="dashboard-menu">
				<li class="left-nav-li active">
					<div class="pointer">
						<div class="arrow"></div>
						<div class="arrow_border"></div>
					</div>
					<a href="index.jsp">
						<i class="icon-fire"></i>
						<span>主页</span>
					</a>
				</li>
				<li class="left-nav-li">
					<a class="dropdown-toggle">
						<i class="icon-comment"></i>
						<span>新闻管理</span>
					</a>
					<ul class="submenu">
						<li>
							<a class="func" href="<%=basePath %>newsManage?method=news_type_sel" target="mainFrame">新闻分类</a>
						</li>
						<li>
							<a class="func" href="<%=basePath %>newsManage?method=news_sel" target="mainFrame">新闻信息</a>
						</li>
						<li>
							<a class="func" href="<%=basePath %>newsManage?method=news_img_sel" target="mainFrame">新闻图片</a>
						</li>
					</ul>
				</li>
				<li class="left-nav-li">
					<a class="dropdown-toggle" href="#">
						<i class="icon-cog"></i>
						<span>产品管理</span>
					</a>
					<ul class="submenu">
						<li>
							<a class="func" href="<%=basePath %>productManage?method=product_type_sel" target="mainFrame">产品分类</a>
						</li>
						<li>
							<a class="func" href="<%=basePath %>productManage?method=product_sel" target="mainFrame">产品信息</a>
						</li>
						<li>
							<a class="func" href="<%=basePath %>productManage?method=product_img_sel" target="mainFrame">产品图片</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- end sidebar -->

		<!-- main container -->
		<div class="content">
			<div class="frame-div">
				<iframe class="frame" id="mainFrame" name="mainFrame" src="fixError.jsp"></iframe>
			</div>
		</div>

		<!-- scripts -->
		<script src="<%=basePath %>js/jquery-ui-1.10.2.custom.min.js"></script>
		<!-- knob -->
		<script src="<%=basePath %>js/jquery.knob.js"></script>
		<!-- flot charts -->
		<script src="<%=basePath %>js/jquery.flot.js"></script>
		<script src="<%=basePath %>js/jquery.flot.stack.js"></script>
		<script src="<%=basePath %>js/jquery.flot.resize.js"></script>

		<script type="text/javascript">
			var height = jQuery(window).height();
			var width = jQuery(window).width();
			$("body").attr("height", height + "px;").attr("width", width + "px;");

			$("#mainFrame").attr("height", height - 60 + "px;");
			$(".left-nav-li").click(function() {
				$(".active").removeClass("active");
				$(".pointer").remove();
				$(".submenu").css("display", "none");
				$(this).children(".submenu").css("display", "block");
				$(this).addClass("active");
				$(this).prepend("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>");

			})
		</script>

	</body>

</html>
