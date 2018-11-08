package lv.helloit.test;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {
    private Map<Integer, User> userMap = new HashMap<>();
    private Integer lastId = 1;

    public void add(User user) {
        user.id = lastId;
        lastId++;
        userMap.put(lastId, user);
    }

    public Collection<User> users() {
        return userMap.values();
    }

    public void delete (Integer id) {
        userMap.remove(id);
    }

    public void update(Integer id, User u) {
        userMap.replace(id, u);
    }
}
