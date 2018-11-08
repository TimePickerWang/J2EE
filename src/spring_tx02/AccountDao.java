package spring_tx02;

public interface AccountDao {
	public void out(String outer, Integer money);

	public void in(String inner, Integer money);
}
