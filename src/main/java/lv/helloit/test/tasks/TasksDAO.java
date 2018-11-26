package lv.helloit.test.tasks;

import java.util.List;
import java.util.Optional;

public interface TasksDAO {
    List<Task> getAll();

    Optional<Task> getById(Long id);

    void insert(Task task);

    void delete(Long id);

    void update(Long taskId, Task task);
}