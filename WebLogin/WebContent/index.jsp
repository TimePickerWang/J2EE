<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- u存在则显示  --%>
	<c:if test="${empty u }">
		<a href="login.jsp">登录</a>
		<a href="reg.jsp">注册</a>
	</c:if>
	<c:if test="${not empty u}">
		欢迎您：${u.name}  <a href="${pageContext.request.contextPath}/loginOutServlet">注销</a>
	</c:if>
</body>
</html>