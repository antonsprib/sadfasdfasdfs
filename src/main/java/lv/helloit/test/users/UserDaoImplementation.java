package lv.helloit.test.users;

import lv.helloit.test.BaseDaoImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImplementation extends BaseDaoImplementation<User> {
    @Autowired
    protected UserDaoImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
