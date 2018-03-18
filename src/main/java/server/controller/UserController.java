package server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import server.model.ApplicationUser;
import server.repository.ApplicationUserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody ApplicationUser user) {
        if (user.getUsername().contains("@methanex.com")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
//    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
//        HttpSession session= request.getSession(false);
//        SecurityContextHolder.clearContext();
//        session= request.getSession(false);
//        if(session != null) {
//            session.invalidate();
//        }
//        for(Cookie cookie : request.getCookies()) {
//            cookie.setMaxAge(0);
//        }
//
//        return "logout";
//    }
}