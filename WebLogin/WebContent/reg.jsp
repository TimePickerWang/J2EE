<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/regServlet" method="post">
		用户名：　<input type="text" name="name" value="${uf.name }"/> ${uf.msg.name }${error }<br/>
		密　码：　<input type="password" name="password"/> ${uf.msg.password } <br/>
		确认密码：<input type="password" name="repassword"/> ${uf.msg.repassword }<br/>
		邮　箱：　<input type="text" name="email" value="${uf.email }"/> ${uf.msg.email }<br/>
		生　日：　<input type="text" name="birthday" value="${uf.birthday }"/> ${uf.msg.birthday } <br/>
		<input type="submit" value="注册"/>
	</form>
</body>
</html>
