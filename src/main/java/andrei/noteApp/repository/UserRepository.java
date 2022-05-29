package andrei.noteApp.repository;

import andrei.noteApp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query("select e from User e where e.login =:login")
    User findByLogin(String login);
}


