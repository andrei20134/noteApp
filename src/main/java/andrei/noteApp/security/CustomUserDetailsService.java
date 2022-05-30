package andrei.noteApp.security;

import andrei.noteApp.model.User;
import andrei.noteApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository dao;

    public CustomUserDetailsService(UserRepository dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User myUser = dao.findByLogin(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }


        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(myUser.getRoles().toString())
                .build();
        return user;
    }
}