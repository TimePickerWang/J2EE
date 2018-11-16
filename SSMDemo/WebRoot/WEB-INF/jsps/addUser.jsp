<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body style="text-align: center;margin-top: 10em;font-size: 20px">
	<div>
		<form action="${pageContext.request.contextPath}/user/addUser.do" method="POST">
			姓名：<input type="text" name="name"> <br/>
			密码：<input type="text" name="password">  <br/>
			邮箱：<input type="text" name="email"> <br/>
			生日：<input type="text" name="birthday"> <br/>
			<input type="submit" value="添加" style="margin-top:10px;position:relative;left:15px;">
		</form>
	</div>
</body>
</html>