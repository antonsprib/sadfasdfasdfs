package lv.helloit.test.tasks;

import lv.helloit.test.BaseDaoImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TasksDAOImplementation extends BaseDaoImplementation<Task> {
    @Autowired
    public TasksDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> getAll() {
        return super.getAll(Task.class);
    }

    public Optional<Task> getById(Long id) {
        return super.getById(id, Task.class);
    }

    public void delete(Long id) {
        super.delete(id, Task.class);
    }
}
