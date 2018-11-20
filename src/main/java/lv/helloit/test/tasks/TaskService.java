package lv.helloit.test.tasks;

import lv.helloit.test.users.User;
import lv.helloit.test.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<TaskView> get() {
        return taskStorage.values().stream().map(this::mapToTaskView).collect(Collectors.toList());
    }

    public TaskView get(Long id) {
        Task task = taskStorage.get(id);

        return mapToTaskView(task);
    }

    private TaskView mapToTaskView(Task task) {
        User user = userService.get(task.getAssignedUserId());

        return new TaskView(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUserId(),
                user == null ? null : user.name + " " + user.lastName);
    }

    public boolean update(Long taskId, Task newTask) {
        if (!taskId.equals(newTask.getId()) && newTask.getId() != null) {
            return false;
        }

        if (taskStorage.containsKey(taskId)) {
            Task oldTask = taskStorage.get(taskId);
            oldTask.setTitle(newTask.getTitle());
            oldTask.setDescription(newTask.getDescription());
            return true;
        } else {
            return false;
        }
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
