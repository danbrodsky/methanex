package server.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server._UserDetails;
import server.model.User;
import server.repository.UserRepository;

import static java.util.Collections.emptyList;

/*!
When a user tries to authenticate, this method receives the username, searches the database
for a record containing it, and (if found) returns an instance of User.
The properties of this instance (username and password) are then checked against the credentials
passed by the user in the login request
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
//            _UserDetails userDetails = new _UserDetails(user.getUsername(), user.getPassword(), emptyList());
//            userDetails.setResourceId(user.getResource().getId());
//            return userDetails;
        }
        throw new UsernameNotFoundException(username);
    }
}
