package andrei.noteApp.repository;

import andrei.noteApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select e from Note e where e.name =:name")
    List<Note> findByName(String name);

    @Query("select e from Note e where e.userId =:userId")
    List<Note> findByUserId(Long userId);

    @Query("select count(e) from Note e where e.userId =:userId")
    long countByUserId(long userId);
}
