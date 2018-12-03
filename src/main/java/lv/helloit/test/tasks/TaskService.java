package lv.helloit.test.tasks;

import lv.helloit.test.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TasksDAOImplementation tasksDAO;

    @Autowired
    public TaskService(TasksDAOImplementation tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    public Long addTask(Task t) {
        t.setCreatedDate(new Date());
        return tasksDAO.insert(t);
    }

    public boolean deleteTask(Long id) {
        if (tasksDAO.getById(id, Task.class).isPresent()) {
            tasksDAO.delete(id, Task.class);
            return true;
        }

        return false;
    }

    public List<Task> get() {
        return tasksDAO.getAll();
    }

    public Optional<Task> get(Long id) {
        return tasksDAO.getById(id);
    }

    public boolean update(Task newTask) {
        tasksDAO.update(newTask);
        return true;
    }

    public boolean assign(Long taskId, Long userId) {
//        Optional<Task> task = tasksDAO.getById(taskId);
//
//        if (task.isPresent()) {
//            Task unwrapped = task.get();
//            unwrapped.setAssignedUserId(userId);
//
//            tasksDAO.update(unwrapped);
//            return true;
//        } else {
//            return false;
//        }

        return false;
    }
}
