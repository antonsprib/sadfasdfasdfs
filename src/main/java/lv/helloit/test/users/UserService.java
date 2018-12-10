package lv.helloit.test.users;

import lv.helloit.test.tasks.Task;
import lv.helloit.test.tasks.TasksDAOImplementation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.util.*;

@Component
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserDaoImplementation userDaoImplementation;
    private final TasksDAOImplementation tasksDAOImplementation;

    @Autowired
    public UserService(UserDaoImplementation userDaoImplementation, TasksDAOImplementation tasksDAOImplementation) {
        this.userDaoImplementation = userDaoImplementation;
        this.tasksDAOImplementation = tasksDAOImplementation;
    }

    public Long add(User user) {
        String password = generatePassword();
        LOGGER.info("Password: " + password);
        sendPasswordEmail(user, password);
        String passwordHash = generatePasswordHash(password);
        LOGGER.info("Password hash: " + passwordHash);
        user.setPasswordHash(passwordHash);

        return userDaoImplementation.insert(user);
    }

    public List<User> users() {
        return userDaoImplementation.getAll();
    }

    public Optional<User> get(Long id) {
        return userDaoImplementation.getById(id);
    }

    public Optional<User> get(String username) {
        return userDaoImplementation.getByUsername(username);
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

    private String generatePassword() {
        return RandomStringUtils.random(8, true, true);
    }

    private void sendPasswordEmail(User user, String password) {
        // todo implement
    }

    private String generatePasswordHash(String password) {
        return Sha512DigestUtils.shaHex(password);
    }
}
