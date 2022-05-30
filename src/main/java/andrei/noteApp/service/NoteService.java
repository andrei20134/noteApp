package andrei.noteApp.service;

import andrei.noteApp.model.Note;
import andrei.noteApp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NoteService {

    // Замена конструктора на аннотацию Autowired, чтобы не делать больших конструкторов
    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    @Transactional
    public List<Note> findNotesByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    @Transactional
    public long countNotes(long userId) {
        return noteRepository.countByUserId(userId);
    }

    @Transactional
    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    @Transactional
    public void saveNote(Note note) {
        if (note == null) {
            System.err.println("Note is null. Are you sure you have connected your form to the application?");
            return;
        }
        noteRepository.save(note);
    }

}
