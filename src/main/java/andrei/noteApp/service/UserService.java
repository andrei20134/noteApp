package andrei.noteApp.service;

import andrei.noteApp.model.Note;
import andrei.noteApp.model.Role;
import andrei.noteApp.model.User;
import andrei.noteApp.repository.NoteRepository;
import andrei.noteApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    // Замена конструктора на аннотацию Autowired, чтобы не делать больших конструкторов
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));;
        userRepository.save(user);
    }
}
