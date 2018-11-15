package lv.helloit.test.tasks;

import lv.helloit.test.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskService {
    private final UserService userService;

    private Map<Long, Task> taskStorage = new HashMap<>();
    private Long lastId = 0L;

    @Autowired
    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public Long addTask(Task t) {
        lastId++;
        t.setId(lastId);
        taskStorage.put(lastId, t);
        return lastId;
    }

    public boolean deleteTask(Long id) {
        if (taskStorage.containsKey(id)) {
            taskStorage.remove(id);
            return true;
        }

        return false;
    }

    public List<Task> get() {
        return new ArrayList<>(taskStorage.values());
    }

    public Task get(Long id) {
        return taskStorage.get(id);
    }

    public boolean assign(Long taskId, Long userId) {
        if (userService.userExists(userId)) {
            taskStorage.get(taskId).setAssignedUserId(userId);
            return true;
        } else {
            return false;
        }
    }
}
