package lv.helloit.test.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService victim;



    @Before
    public void setUp() {
        victim = new UserService();
    }

    @Test
    public void shouldAddNewUser() {
        User u = new User();
        u.age = 19;
        u.name = "Vasja";
        u.lastName = "Pupkin";

        victim.add(u);

        Collection<User> users = victim.users();
        assertEquals(users.size(), 1);

        User savedUser = users.stream().findFirst().get();

        assertEquals(savedUser.name, "Vasja");
    }
}