package andrei.noteApp.view;

import andrei.noteApp.model.Note;
import andrei.noteApp.model.User;
import andrei.noteApp.security.SecurityService;
import andrei.noteApp.service.NoteService;
import andrei.noteApp.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;



@Component
@Scope("prototype")
@Route(value="", layout = MainLayout.class)
@PageTitle("Notes | Сервис заметок")
@PermitAll
public class NoteView extends VerticalLayout {
    Grid<Note> grid = new Grid<>(Note.class,false);
    NoteForm form;
    private NoteService noteService;
    private SecurityService securityService;
    private UserService userService;

    private User user = null;

    public NoteView(NoteService service, SecurityService securityService, UserService userService) {
        this.noteService = service;
        this.securityService = securityService;
        this.userService = userService;
        addClassName("note-view");
        setSizeFull();
        configureGrid();

        form = new NoteForm();
        form.setWidth("25em");
        form.addListener(NoteForm.SaveEvent.class, this::saveNote);
        form.addListener(NoteForm.DeleteEvent.class, this::deleteNote);
        form.addListener(NoteForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();


        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String currentPrincipalName = authentication.getName();

        UserDetails authenticatedUser =  securityService.getAuthenticatedUser();

        if(authenticatedUser != null) {
            user = userService.findByLogin(authenticatedUser.getUsername());
        }


        add(getToolbar(), content);
        updateList();
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event ->
                editNote(event.getValue()));

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        //grid.setColumns("name", "description");

        grid.addColumn(Note::getName).setHeader("Название заметки");
        grid.addColumn(Note::getDescription).setHeader("Описание");

        //var a = grid.getColumns();

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        Button addNoteButton = new Button("Добавить заметку");
        addNoteButton.addClickListener(click -> addNote());

        HorizontalLayout toolbar = new HorizontalLayout(addNoteButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveNote(NoteForm.SaveEvent event) {

        Note noteToSave = event.getNote();
        noteToSave.setUser(user);

        noteService.saveNote(noteToSave);
        updateList();
        closeEditor();
    }

    private void deleteNote(NoteForm.DeleteEvent event) {
        noteService.deleteNote(event.getNote());
        updateList();
        closeEditor();
    }

    public void editNote(Note note) {
        if (note == null) {
            closeEditor();
        } else {
            form.setNote(note);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addNote() {
        grid.asSingleSelect().clear();
        editNote(new Note());
    }

    private void closeEditor() {
        form.setNote(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(noteService.findNotesByUserId(user.getId()));
    }
}