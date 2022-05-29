package andrei.noteApp.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


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
    private Long userId;
}
