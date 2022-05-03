package andrei.noteApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;

    /*@OneToMany
    private  List<Note> notes = new ArrayList<>();*/
}

