package cn.wang.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.wang.model.User;
import cn.wang.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/findAllUsers")
	public String findAllUsers(Model model) {
		List<User> list = userService.findAllUsers();
		model.addAttribute("users", list);
		return "users";
	}

	@RequestMapping("/findUserById")
	public User findUserById(Integer id) {
		User user = userService.findUserById(id);
		return user;
	}

	// ���������û�ҳ��
	@RequestMapping("/toAddUser")
	public String toAddUser(Model model) {
		return "addUser";
	}

	// �����û�
	@RequestMapping("/addUser")
	public String addUser(User user) {
		userService.addUser(user);
		return "redirect:/user/findAllUsers.do";
	}

	// ɾ���û�
	@RequestMapping("/deleteUserById/{id}")
	public String deleteUserById(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return "redirect:/user/findAllUsers.do";
	}

	// �����޸��û�ҳ��
	@RequestMapping("/toUpdataUser/{id}")
	public String toUpdataUser(@PathVariable Integer id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	// �޸��û�
	@RequestMapping("/updataUser")
	public String updataUser(User user) {
		userService.updataUser(user);
		return "redirect:/user/findAllUsers.do";
	}

}
