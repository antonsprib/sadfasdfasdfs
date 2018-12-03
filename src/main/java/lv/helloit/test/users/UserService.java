package lv.helloit.test.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {
    private final UserDaoImplementation userDaoImplementation;

    @Autowired
    public UserService(UserDaoImplementation userDaoImplementation) {
        this.userDaoImplementation = userDaoImplementation;
    }

    public Long add(User user) {
        return userDaoImplementation.insert(user);
    }

    public List<User> users() {
        List<User> users = userDaoImplementation.getAll();
        return users;
    }

    public Optional<User> get(Long id) {
        return userDaoImplementation.getById(id);
    }

    public void delete (Long id) {
        userDaoImplementation.delete(id);
    }

    public void update(Long id, User u) {
        u.setId(id);
        userDaoImplementation.update(u);
    }
}
