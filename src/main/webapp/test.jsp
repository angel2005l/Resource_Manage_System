<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/xh_bi_phn_rms/img?method=product_img_ins" method="post" enctype="multipart/form-data">
		<input type="text" name="title" /> 
		<input type="text" name="is_main" /> 
		<input type="text" name="status"/>
		<input type="text" name="sort" /> 
		<input type="file" name="file1" /> 
		<input type="file" name="file2" />
		<select name="pid">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select>
		<input type="submit" value="submit">
	</form>
</body>
</html>