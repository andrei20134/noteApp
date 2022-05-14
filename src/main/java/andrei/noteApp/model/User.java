package andrei.noteApp.model;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
// Модель пользователя
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.login = username;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return id;
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
}

