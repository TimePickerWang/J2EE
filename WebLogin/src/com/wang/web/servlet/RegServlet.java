package com.wang.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import com.wang.domain.User;
import com.wang.domain.UserForm;
import com.wang.exception.UserExistException;
import com.wang.service.UserService;
import com.wang.service.impl.UserServiceImpl;

@WebServlet("/regServlet")
public class RegServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		UserForm uf = new UserForm();
		try {
			BeanUtils.populate(uf, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!uf.validate()) {// 如果map中不为空，说明有错误信息
			request.setAttribute("uf", uf);
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
			return;
		}
		User user = new User();
		try {
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			BeanUtils.populate(user, request.getParameterMap());
			// 调用业务逻辑
			UserService us = new UserServiceImpl();
			// 查看用户名是否已被注册
			us.findUserByName(user.getName());
			us.register(user);
		} catch (UserExistException e) {
			request.setAttribute("error", "用户名已存在");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 分发转向
		response.getWriter().write("注册成功！1秒跳转到主页");
		response.setHeader("refresh", "1;url=" + request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
