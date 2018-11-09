package aop_aspect_xml;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("aop_aspect_xml_addUser");
	}

	@Override
	public String updataUser() {
		System.out.println("aop_aspect_xml_updataUser");
//		int i = 1 / 0; // 用于测试通知afterThrowing,after
		return "hello world";
	}

	@Override
	public void deleteUser() {
		System.out.println("aop_aspect_xml_deleteUser");
	}

}
