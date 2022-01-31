package by.epam.tc.web.entity.user;

import java.util.Objects;

public class Admin extends User {
	private static final long serialVersionUID = 1L;

	private String name;
	private String photoPath;

	public Admin() {
	}

	public Admin(String name) {
		this.name = name;
		this.setRole(Role.ADMIN);
	}

	public Admin(String name, String photoPath) {
		this.setRole(Role.ADMIN);
		this.name = name;
		this.photoPath = photoPath;
	}

	public Admin(int id, String login, String name) {
		super(login, Role.ADMIN);
		this.setUserId(id);
		this.name = name;
	}

	public Admin(int id, String login, String name, String photoPath) {
		super(login, Role.ADMIN);
		this.setUserId(id);
		this.name = name;
		this.photoPath = photoPath;
	}

	public Admin(String login, String password, String name, String photoPath) {
		super(login, password, Role.ADMIN);
		this.name = name;
		this.photoPath = photoPath;
	}

	public Admin(String login, String name, String photoPath) {
		super(login, Role.ADMIN);
		this.name = name;
		this.photoPath = photoPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, photoPath);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(name, other.name) && Objects.equals(photoPath, other.photoPath);
	}

	@Override
	public String toString() {
		return "Admin{" + "id=" + getUserId() + ", name='" + name + '\'' + ", photoPath='" + photoPath + '\'' + '}';
	}
}
