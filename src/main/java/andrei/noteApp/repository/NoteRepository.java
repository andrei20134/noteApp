package andrei.noteApp.repository;

import andrei.noteApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("from Note e")
    List<Note> findByName(String name);

    List<Note> findByUserId(Long id);

    long countByUserId(long id);
}
