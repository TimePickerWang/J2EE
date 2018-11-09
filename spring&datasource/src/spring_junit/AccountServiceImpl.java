package spring_junit;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String inner, String outer, Integer money) {
		accountDao.out(outer, money);
		accountDao.in(inner, money);
	}

}
