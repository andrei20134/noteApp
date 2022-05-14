package andrei.noteApp.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
