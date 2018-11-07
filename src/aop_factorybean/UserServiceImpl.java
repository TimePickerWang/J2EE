package aop_factorybean;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("aop_factorybean_addUser");
	}

	@Override
	public void updataUser() {
		System.out.println("aop_factorybean_updataUser");
	}

	@Override
	public void deleteUser() {
		System.out.println("aop_factorybean_deleteUser");
	}

}
