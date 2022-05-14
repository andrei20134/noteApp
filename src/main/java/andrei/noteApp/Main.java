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
			//userService.save(new User("andrei0", "1234", Role.ADMIN));
			//userService.save(new User("andrei", "123", Role.USER));


		};

	}
}