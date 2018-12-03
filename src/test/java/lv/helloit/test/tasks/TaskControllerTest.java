package lv.helloit.test.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    @Test
    public void shouldCreateTask() {
        Task input = new Task();
        input.setCreatedDate(new Date());
        input.setTitle("Task from test");
        input.setDescription("descr");

        taskController.create(input);
        Optional<Task> task = taskController.getById(1L);

        assertThat(task).isPresent();
        assertThat(task.get().getTitle()).isEqualTo("Task from test");
        assertThat(task.get().getDescription()).isEqualTo("descr");
        assertThat(task.get().getUser().getId()).isNull();
    }
}