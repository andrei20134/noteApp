package andrei.noteApp.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    /*@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/
}
