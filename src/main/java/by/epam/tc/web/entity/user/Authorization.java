package by.epam.tc.web.entity.user;

import java.util.Objects;

public class Authorization {

	private String login;
	private String password;

	public Authorization() {

	}

	public Authorization(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authorization other = (Authorization) obj;
		return Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}
}
