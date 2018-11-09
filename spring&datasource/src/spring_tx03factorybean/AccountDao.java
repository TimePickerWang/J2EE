package spring_tx03factorybean;

public interface AccountDao {
	public void out(String outer, Integer money);

	public void in(String inner, Integer money);
}
