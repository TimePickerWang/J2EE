package aop_jdk;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("aop_jdk_addUser");
	}

	@Override
	public void updataUser() {
		System.out.println("aop_jdk_updataUser");
	}

	@Override
	public void deleteUser() {
		System.out.println("aop_jdk_deleteUser");
	}

}
