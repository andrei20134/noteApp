package andrei.noteApp.view;

import andrei.noteApp.model.Note;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class NoteForm extends FormLayout {
    private Note note;

    TextField name = new TextField("Название");
    TextField description = new TextField("Описание");
    Binder<Note> binder = new BeanValidationBinder<>(Note.class);

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    public NoteForm() {
        addClassName("note-form");
        binder.bindInstanceFields(this);


        add(name,
                description,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, note)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setNote(Note note) {
        this.note = note;
        binder.readBean(note);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(note);
            fireEvent(new SaveEvent(this, note));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class NoteFormEvent extends ComponentEvent<NoteForm> {
        private Note note;

        protected NoteFormEvent(NoteForm source, Note note) {
            super(source, false);
            this.note = note;
        }

        public Note getNote() {
            return note;
        }
    }

    public static class SaveEvent extends NoteFormEvent {
        SaveEvent(NoteForm source, Note note) {
            super(source, note);
        }
    }

    public static class DeleteEvent extends NoteFormEvent {
        DeleteEvent(NoteForm source, Note note) {
            super(source, note);
        }

    }

    public static class CloseEvent extends NoteFormEvent {
        CloseEvent(NoteForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}