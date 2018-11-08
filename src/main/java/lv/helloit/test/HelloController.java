package lv.helloit.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class HelloController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @GetMapping
    public Collection<User> users() {
        return userService.users();
    }

    @DeleteMapping(value = "/{id}")
    public void delete (@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam Integer id, @RequestParam User u) {
        userService.update(id, u);
    }
}
