package andrei.noteApp.view;

import andrei.noteApp.repository.NoteRepository;
import andrei.noteApp.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("Info")
@Route(value = "info", layout = MainLayout.class)
public class InfoView extends HorizontalLayout {
    private TextField noteCount;
    //private Button sayHello;
    private  NoteService noteService;

    public InfoView(NoteService noteService) {
        this.noteService = noteService;
        noteCount = new TextField("Количество заметок: " );
        noteCount.setValue(Long.toString(noteService.countNotes()));

       // sayHello = new Button("Say hello");
        //sayHello.addClickListener(e -> {
        //    Notification.show("Hello " + name.getValue());
       // });

                setMargin(true);
        setVerticalComponentAlignment(Alignment.END, noteCount);

        add(noteCount);
    }
}
