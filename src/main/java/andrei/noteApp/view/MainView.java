/*
package andrei.noteApp.view;

import andrei.noteApp.model.User;
import andrei.noteApp.repository.UserRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

//@Route
public class MainView extends Composite<LoginOverlay> {
    private UserRepository userRepo;

    @Autowired
    public MainView(UserRepository userRepo) {
        this.userRepo = userRepo;



        LoginOverlay loginOverlay = getContent();
        loginOverlay.setOpened(true);

        loginOverlay.addLoginListener(loginEvent -> {

            var test= userRepo.findByLoginAndPassword(loginEvent.getUsername(), loginEvent.getPassword());
            if(test == null) {
                User user = new User();
                user.setLogin(loginEvent.getUsername());
                user.setPassword(loginEvent.getPassword());
                userRepo.save(user);
            }
            UI.getCurrent().navigate("note");
        });
    }

}
*/
