package by.epam.tc.web.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private Role role;

    public User() {}

    public User(String login, Role role) {
        this.login = login;
        this.role = role;
    }
    
    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = Role.valueOf(role);
    }

    public User(int id, String login, Role role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
