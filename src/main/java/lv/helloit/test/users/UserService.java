package lv.helloit.test.users;

import lv.helloit.test.tasks.Task;
import lv.helloit.test.tasks.TasksDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {
    private final UserDaoImplementation userDaoImplementation;
    private final TasksDAOImplementation tasksDAOImplementation;

    @Autowired
    public UserService(UserDaoImplementation userDaoImplementation, TasksDAOImplementation tasksDAOImplementation) {
        this.userDaoImplementation = userDaoImplementation;
        this.tasksDAOImplementation = tasksDAOImplementation;
    }

    public Long add(User user) {
        String password = generatePassword();
        sendPasswordEmail(user, password);
        String passwordHash = generatePasswordHash(password);
        user.setPasswordHash(passwordHash);

        return userDaoImplementation.insert(user);
    }

    private String generatePassword() {
        // todo implement
        return "";
    }

    private void sendPasswordEmail(User user, String password) {
        // todo implement
    }

    private String generatePasswordHash(String password) {
        // todo implement
        return "";
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

    public void assignTask(Long userId, Long taskId) {
        Optional<User> wrappedUser = this.get(userId);
        Optional<Task> wrappedTask = tasksDAOImplementation.getById(taskId);

        if (wrappedTask.isPresent() && wrappedUser.isPresent()) {
            User user = wrappedUser.get();
            user.getTasks().add(wrappedTask.get());

            userDaoImplementation.update(user);
        }
    }
}
