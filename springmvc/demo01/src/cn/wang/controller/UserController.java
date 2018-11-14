package cn.wang.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.wang.domain.User;

@Controller   //<bean class="UserController"/>
@RequestMapping("/user")  //跟路径，用于隔离controller里相同的请求
public class UserController {
	
	@RequestMapping("/toSuccess")
	public String toSuccess(){
		return "success";
	}
	
	// 获取字符串参数
	@RequestMapping("/getStr")
	public String getStr(String str){
		System.out.println(str);
		return "success";
	}
	
	// 获取pojp类型参数
	@RequestMapping("/getUser")
	public String getUser(User user){
		System.out.println(user);
		return "success";
	}
	
	// 请求回显
	@RequestMapping("/getUsers")
	public String getUserList(Model model){
		
		List<User> userList = new ArrayList<User>();
		
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("张三");
		user1.setAge(11);
		
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("李四");
		user2.setAge(15);
		
		userList.add(user1);
		userList.add(user2);
		
		model.addAttribute("users", userList);
		
		return "users";
	}
	
	//Restfull
	@RequestMapping("/updataUser/{id}")
	public String updataUser(@PathVariable Integer id){
		
		System.out.println(id);
		// 重定向
		return "redirect:/user/getUsers.do";
	}
	
}
