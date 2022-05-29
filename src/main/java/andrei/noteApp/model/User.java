package andrei.noteApp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
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
}

