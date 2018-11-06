package lv.helloit.test;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/hello")
    public User handleHello() {
        return new User("Vasja", "P");
    }

    @RequestMapping("/goodbye")
    public String handleGoodbye() {
        return "Goodbye, world";
    }
}
