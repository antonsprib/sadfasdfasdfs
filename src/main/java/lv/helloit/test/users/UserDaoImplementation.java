package lv.helloit.test.users;

import lv.helloit.test.BaseDaoImplementation;
import lv.helloit.test.tasks.Task;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImplementation extends BaseDaoImplementation<User> {
    @Autowired
    protected UserDaoImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> getAll() {
        return super.getAll(User.class);
    }

    public Optional<User> getById(Long id) {
        return super.getById(id, User.class);
    }

    public void delete(Long id) {
        super.delete(id, User.class);
    }
}
