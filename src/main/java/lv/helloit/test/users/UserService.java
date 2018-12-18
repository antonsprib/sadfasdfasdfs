package lv.helloit.test.users;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import lv.helloit.test.tasks.Task;
import lv.helloit.test.tasks.TasksDAOImplementation;

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
        return userDaoImplementation.getByEmail(username);
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
        RestTemplate restTemplate = new RestTemplate();

        URI url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8888)
                .path("sendTextMail")
                .queryParam("recipientAddress", user.getEmail())
                .queryParam("subject", "Your password")
                .queryParam("body", "Your password is: " + password)
                .build("");

        String response = restTemplate.getForObject(url, String.class);

        LOGGER.info(response);
    }

    private String generatePasswordHash(String password) {
        return Sha512DigestUtils.shaHex(password);
    }
}
