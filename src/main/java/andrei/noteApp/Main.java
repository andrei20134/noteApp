package andrei.noteApp;

import andrei.noteApp.model.Note;
import andrei.noteApp.repository.NoteRepository;
import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication()
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	/*// Начальная инициализация БД
	@Bean
	public CommandLineRunner loadData(NoteRepository repository) {
		return (args) -> {

			Note note1= new Note();
			note1.setName("Заметка №1");
			note1.setDescription("Описание заметки 1");

			Note note2= new Note();
			note2.setName("Заметка №2");
			note2.setDescription("Описание заметки 2");

			Note note3= new Note();
			note3.setName("Заметка №3");
			note3.setDescription("Описание заметки 3");


			repository.save(note1);
			repository.save(note2);
			repository.save(note3);
		};
	}*/
}
