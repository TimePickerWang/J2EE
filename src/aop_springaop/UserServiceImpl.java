package aop_springaop;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("aop_springaop_addUser");
	}

	@Override
	public void updataUser() {
		System.out.println("aop_springaop_updataUser");
	}

	@Override
	public void deleteUser() {
		System.out.println("aop_springaop_deleteUser");
	}

}
