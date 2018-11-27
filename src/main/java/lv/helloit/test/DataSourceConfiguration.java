package lv.helloit.test;

import lv.helloit.test.tasks.Task;
import lv.helloit.test.users.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Task.class)
                .configure()
                .buildSessionFactory();
    }
}
