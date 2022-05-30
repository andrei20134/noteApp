package andrei.noteApp;

import andrei.noteApp.model.Note;
import andrei.noteApp.model.Role;
import andrei.noteApp.model.User;
import andrei.noteApp.repository.NoteRepository;
import andrei.noteApp.repository.UserRepository;
import andrei.noteApp.service.UserService;
import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

//@SpringBootApplication()
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
public class Main {

	@Bean
	BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}



	//Начальная инициализация БД
	@Bean
	public CommandLineRunner loadData(NoteRepository repository, UserService userService) {
		return (args) -> {


			// Начальная инициализация

//			// Роли нового пользователя admin
//			HashSet<Role> adminRolesSet = new HashSet<>();
//			adminRolesSet.add(Role.ADMIN);
//			adminRolesSet.add(Role.DEALER);
//
//			// Роли нового пользователя user
//			HashSet<Role> userRolesSet = new HashSet<>();
//			userRolesSet.add(Role.USER);
//			userRolesSet.add(Role.CLIENT);
//
//			userService.save(new User("andrei0", "1234", adminRolesSet));
//			userService.save(new User("andrei", "123", userRolesSet));


		};

	}
}