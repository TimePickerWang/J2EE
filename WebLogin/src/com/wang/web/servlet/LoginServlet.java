package com.wang.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import com.wang.domain.User;
import com.wang.service.UserService;
import com.wang.service.impl.UserServiceImpl;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		User user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserService userService = new UserServiceImpl();
		User u = userService.login(user);
		if (u != null) {// 登录成功，把user对象放到session中
			request.getSession().setAttribute("u", user);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "<p style='color:red'>用户名或密码错误</p>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
