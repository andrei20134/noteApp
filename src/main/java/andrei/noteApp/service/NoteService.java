package andrei.noteApp.service;

import andrei.noteApp.model.Note;
import andrei.noteApp.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;


    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;

    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> findNotesByUserId(Long id) {
        return noteRepository.findByUserId(id);
    }

    public long countNotes(long id) {
        return noteRepository.countByUserId(id);
    }

    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    public void saveNote(Note note) {
        if (note == null) {
            System.err.println("Note is null. Are you sure you have connected your form to the application?");
            return;
        }
        noteRepository.save(note);
    }

}
