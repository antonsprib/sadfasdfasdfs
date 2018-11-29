package lv.helloit.test.tasks;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class TaskDAOTestImplementation implements TasksDAO {
    Task t;


    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Optional<Task> getById(Long id) {
        return Optional.ofNullable(t);
    }

    @Override
    public Long insert(Task task) {
        t = task;
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Task task) {

    }
}
