<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="tag" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgRootUrl="http://img.cdn.xinhaimedical.cn/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link href="<%=basePath%>css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath%>css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="<%=basePath%>css/bootstrap/bootstrap-overrides.css"	type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/layout.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/elements.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icons.css" />

<!-- libraries -->
<link href="<%=basePath%>css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script src="<%=basePath%>js/jquery-latest.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/theme.js"></script>

<script type="text/javascript" src="<%=basePath%>js/layer.js"></script>
</head>
<body>
</body>
</html>