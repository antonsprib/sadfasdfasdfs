package lv.helloit.test.users;

import lv.helloit.test.BaseDaoImplementation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public Optional<User> getByUsername(String username) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("username"), username));
        query.select(root);

        User u = session.createQuery(query).getSingleResult();
        return Optional.ofNullable(u);
    }

    public void delete(Long id) {
        super.delete(id, User.class);
    }
}
