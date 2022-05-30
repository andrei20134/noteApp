package andrei.noteApp.service;

import andrei.noteApp.model.User;
import andrei.noteApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
