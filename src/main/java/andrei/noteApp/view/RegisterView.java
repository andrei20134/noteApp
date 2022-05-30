package andrei.noteApp.view;

import andrei.noteApp.model.Role;
import andrei.noteApp.model.User;
import andrei.noteApp.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;

import java.util.HashSet;
import java.util.Set;

@Route("register")
public class RegisterView extends Composite  {

    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected Component initContent() {
        TextField username = new TextField("Логин");
        PasswordField password1 = new PasswordField("Пароль");
        PasswordField password2 = new PasswordField("Пароль");
        var layout = new VerticalLayout(
                new H2("Регистрация"),
                username,
                password1,
                password2,
                new Button("Потвердить", event -> register(
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue()
                ))
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        return layout;
    }

    private void register(String username, String password1, String password2) {
        if (username.trim().isEmpty()) {
            Notification.show("Введите логин");
        } else if (password1.isEmpty()) {
            Notification.show("Введите пароль");
        } else if (!password1.equals(password2)) {
            Notification.show("Пароли не совпадают");
        } else {

            // Роли по умолчанию для нового пользователя
            HashSet<Role> rolesSet = new HashSet<>();
            rolesSet.add(Role.USER);
            rolesSet.add(Role.CLIENT);

            userService.save(new User(username, password1, rolesSet));
            Notification.show("Регистрация завершена");

        }
    }
}