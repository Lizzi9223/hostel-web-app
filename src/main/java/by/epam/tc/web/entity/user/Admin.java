package by.epam.tc.web.entity.user;

import java.util.Objects;

public class Admin extends User{
    private String name;
    private String photoPath;

    public Admin(){}

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
    
    public Admin(String login, String password, String name, String photo) {
    	super(login, password, Role.ADMIN);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return this.getUserId() == admin.getUserId() && Objects.equals(name, admin.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId(), name);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getUserId() +
                ", name='" + name + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
