package andrei.noteApp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notes")
// Модель заметки
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
