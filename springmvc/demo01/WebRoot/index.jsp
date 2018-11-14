<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>springmvc/</title>
</head>
<body>

	<h1>欢迎访问springmvc</h1>

	<form action="${pageContext.request.contextPath }/user/getStr.do" style="margin-bottom:20px" method="post">
		姓名：<input type="text" name="str" id="str">
		<input type="submit" value="提交">
	</form>
	
	
	----------------test-----------------



	<form action="${pageContext.request.contextPath}/user/getUser.do" style="margin-top:20px" method="post">
		姓名：<input type="text" name="username" id="username">
		年龄：<input type="text" name="age" id="age">
		<input type="submit" value="提交">
	</form>


</body>
</html>