package lv.helloit.test.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
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
    public void delete (@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam Long id, @RequestBody User u) {
        userService.update(id, u);
    }
}
