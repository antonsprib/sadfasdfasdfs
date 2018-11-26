package lv.helloit.test.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    @Test
    public void shouldCreateTask() {
        taskController.create(new Task(null, "Task from test", "descr", null));

        Task task = taskController.getById(1L);

        assertThat(task.getTitle()).isEqualTo("Task from test");
        assertThat(task.getDescription()).isEqualTo("descr");
        assertThat(task.getAssignedUserId()).isNull();
    }
}