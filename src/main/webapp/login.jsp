<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/xh_bi_phn_rms/test" method="post">
			<input type="hidden" name="method" value="next" />
		<table border="2px">
			<tr>
				<td>窗口号</td>
				<td><input type="text" id="winNo" name="winNo" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="开启" /></td>
			</tr>
		</table>
	</form>
</body>
</html>