<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>users</title>
</head>
<style type="text/css">
.tdClass{
    border: 1px solid #cccccc
}
</style>
<body style="display: flex;margin-top: 10em;">

	<table style="display: flex;margin: auto;font-size: 20px;text-align: center;border-collapse:collapse;">
		<tr>
			<td class="tdClass">ID</td>
			<td class="tdClass">年龄</td>
			<td class="tdClass">邮箱</td>
			<td class="tdClass">生日</td>
			<td class="tdClass">操作</td>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
				<td class="tdClass">${user.id}</td>
				<td class="tdClass">${user.name }</td>
				<td class="tdClass">${user.email }</td>
				<td class="tdClass">${user.birthday }</td>
				<td class="tdClass">
					<a href="${pageContext.request.contextPath}/rest/user/toUpdataUser/${user.id}">修改</a>/
					<a href="${pageContext.request.contextPath}/rest/user/deleteUserById/${user.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
				<a href="${pageContext.request.contextPath}/user/toAddUser.do">新增用户</a>
			</td>
		</tr>
		
	</table>
	
</body>
</html>