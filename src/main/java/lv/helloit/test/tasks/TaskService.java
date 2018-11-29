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
    private final TasksDAO tasksDAO;

    @Autowired
    public TaskService(UserService userService, TasksDAO tasksDAO) {
        this.userService = userService;
        this.tasksDAO = tasksDAO;
    }

    public Long addTask(Task t) {
        t.setCreatedDate(new Date());
        return tasksDAO.insert(t);
    }

    public boolean deleteTask(Long id) {
        if (tasksDAO.getById(id).isPresent()) {
            tasksDAO.delete(id);
            return true;
        }

        return false;
    }

    public List<TaskView> get() {
        return tasksDAO.getAll().stream().map(this::mapToTaskView).collect(Collectors.toList());
    }

    public TaskView get(Long id) {
        Optional<Task> task = tasksDAO.getById(id);

        if (task.isPresent()) {
            return mapToTaskView(task.get());
        } else {
            return null;
        }
    }

    private TaskView mapToTaskView(Task task) {
        User user = userService.get(task.getAssignedUserId());

        return new TaskView(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUserId(),
                task.getCreatedDate(),
                user == null ? null : user.getName() + " " + user.getLastName());
    }

    public boolean update(Long taskId, Task newTask) {
        if (!taskId.equals(newTask.getId()) && newTask.getId() != null) {
            return false;
        }

        Optional<Task> oldTask = tasksDAO.getById(taskId);
        if (oldTask.isPresent()) {
            tasksDAO.update(taskId, newTask);
        }
        return true;
    }

    public boolean assign(Long taskId, Long userId) {
        Optional<Task> task = tasksDAO.getById(taskId);

        if (task.isPresent()) {
            Task unwrapped = task.get();
            unwrapped.setAssignedUserId(userId);

            tasksDAO.update(taskId, unwrapped);
            return true;
        } else {
            return false;
        }
    }
}
