package spring_tx03factorybean;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String inner, String outer, Integer money) {
		accountDao.out(outer, money);
//		int i = 1 / 0;
		accountDao.in(inner, money);
	}

}
