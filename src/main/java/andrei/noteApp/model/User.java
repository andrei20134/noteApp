package andrei.noteApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


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


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, Set<Role> roles) {
        this.login = username;
        this.roles = roles;
        this.password = password;
    }
}

