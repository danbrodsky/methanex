package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class WebController {
    @Autowired
    private ResourceRepository repository;

    @RequestMapping("/")
    public String test() {
        Resource test0 = new Resource();
        test0.setName("Boobs McGee The Original");
        test0.setLocation("Boobtown");
        test0.setEmail("boobs@boobs.com");

        Resource test1 = new Resource();
        test1.setName("Boobs McGee The Best");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        Resource test2 = new Resource();
        test1.setName("Boobs McGee The Karate Master");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        Resource test3 = new Resource();
        test1.setName("Boobs McGee The Kimchi Muncher");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        Resource test4 = new Resource();
        test1.setName("Boobs McGee The I'm So Bored");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        Resource test5 = new Resource();
        test1.setName("Boobs McGee Why So Many Boobs");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        Resource test6 = new Resource();
        test1.setName("BOOOOOOOOOOOOBS");
        test1.setLocation("Boobtown");
        test1.setEmail("boobs@boobs.com");

        repository.save(new ArrayList<>(Arrays.asList(test0, test1, test2, test3, test4, test5, test6)));
        return "Done";
    }

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public @ResponseBody Iterable<Resource> getResources() {
        return repository.findAll();
    }



}