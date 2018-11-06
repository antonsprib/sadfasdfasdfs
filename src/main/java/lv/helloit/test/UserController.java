package lv.helloit.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String[] getUsers() {
        String[] users = {"U1", "U2", "U3"};
        return users;
    }

    @GetMapping("/2")
    public String[] getUser2() {
        String[] users = {"U3", "U6", "U4"};
        return users;
    }
}
