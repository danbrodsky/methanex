package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import server.model.Resource;
import server.model.User;
import server.repository.UserRepository;
import server.repository.ResourceRepository;

@CrossOrigin
@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Resource resource = new Resource();
        resource.setEmail(user.getUsername());
        user.setResource(new Resource());
        resourceRepository.save(resource);
        userRepository.save(user);

        return ResponseEntity.accepted().build();
    }
}