package lv.helloit.test.users;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {
    private Map<Long, User> userMap = new HashMap<>();
    private Long lastId = 0L;

    public Long add(User user) {
        lastId++;
        user.setId(lastId);
        userMap.put(lastId, user);
        return lastId;
    }

    public boolean userExists(Long id) {
        return userMap.containsKey(id);
    }

    public List<User> users() {
        return new ArrayList<>(userMap.values());
    }

    public User get(Long id) {
        return userMap.get(id);
    }

    public void delete (Long id) {
        userMap.remove(id);
    }

    public void update(Long id, User u) {
        userMap.replace(id, u);
    }
}
