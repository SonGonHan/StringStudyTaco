package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.entity.User;
import tacos.data.UserRepository;

@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public UserRepositoryUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(username);
    }
}
