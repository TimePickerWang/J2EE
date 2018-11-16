package cn.wang.model;

public class User {
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String birthday;

	public User() {
		super();
	}

	public User(Integer id, String name, String password, String email, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
