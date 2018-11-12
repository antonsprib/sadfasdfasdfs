package lv.helloit.test.tasks;

import lv.helloit.test.users.User;
import lv.helloit.test.users.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService victim;

    @Autowired
    private UserService userService;

    @Test
    public void shouldAssignTask() {
        Task t = new Task(null, "Task 1", "descr", null);
        Long taskId = victim.addTask(t);
        Long userId = userService.add(new User());

        victim.assign(taskId, userId);
        assertEquals(userId, victim.get().get(0).getAssignedUserId());
    }
}