package lv.helloit.test.tasks;

import lv.helloit.test.users.User;
import lv.helloit.test.users.UserDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TasksDAOImplementation tasksDAO;
    private final UserDaoImplementation userDao;

    @Autowired
    public TaskService(TasksDAOImplementation tasksDAO, UserDaoImplementation userDao) {
        this.tasksDAO = tasksDAO;
        this.userDao = userDao;
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
        Optional<Task> wrappedTask = this.get(taskId);
        Optional<User> wrappedUser = userDao.getById(userId);

        if (wrappedTask.isPresent() && wrappedUser.isPresent()) {
            Task task = wrappedTask.get();
            task.setUser(wrappedUser.get());

            this.update(task);
            return true;
        }

        return false;
    }
}
