package lv.helloit.test.tasks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class TasksDAOImplementation implements TasksDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        query.select(query.from(Task.class));

        List<Task> tasks = session.createQuery(query).getResultList();
        session.close();
        return tasks;
    }

    @Override
    public Optional<Task> getById(Long id) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.where(builder.equal(root.get("id"), id));
        query.select(root);

        Optional<Task> task = Optional.ofNullable(session.createQuery(query).getSingleResult());
        session.close();
        return task;
    }

    @Override
    public void insert(Task task) {
        // todo implement
    }

    @Override
    public void delete(Long id) {
        // todo implement
    }

    @Override
    public void update(Long taskId, Task task) {
        // todo implement
    }
}
