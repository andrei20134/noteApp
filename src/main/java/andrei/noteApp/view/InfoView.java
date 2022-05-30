package andrei.noteApp.view;

import andrei.noteApp.model.User;
import andrei.noteApp.security.SecurityService;
import andrei.noteApp.service.NoteService;
import andrei.noteApp.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.userdetails.UserDetails;


@PageTitle("Info")
@Route(value = "info", layout = MainLayout.class)
public class InfoView extends HorizontalLayout {
    private TextField noteCount;
    //private Button sayHello;
    private  NoteService noteService;

    private UserService userService;

    private  SecurityService securityService;

    private User user= null;

    public InfoView(NoteService noteService, UserService userService, SecurityService securityService) {
        this.noteService = noteService;
        this.userService = userService;
        this.securityService = securityService;
        UserDetails authenticatedUser =  securityService.getAuthenticatedUser();

        if(authenticatedUser != null) {
            user = userService.findByLogin(authenticatedUser.getUsername());
        }

        TextArea textArea = new TextArea();
        textArea.setWidthFull();
        textArea.setLabel("Описание");
        textArea.setValue("Количество заметок пользователя " + authenticatedUser.getUsername() + ": " + noteService.countNotes(user.getId()));
        add(textArea);




        //noteCount = new TextField("Количество заметок пользователя " + authenticatedUser.getUsername() + " : " );
        //noteCount.setValue(Long.toString(noteService.countNotes(userId)));

       // sayHello = new Button("Say hello");
        //sayHello.addClickListener(e -> {
        //    Notification.show("Hello " + name.getValue());
       // });

                //setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, noteCount);

        //add(noteCount);
    }
}
