package spring_tx05anno;

public interface AccountDao {
	public void out(String outer, Integer money);

	public void in(String inner, Integer money);
}
