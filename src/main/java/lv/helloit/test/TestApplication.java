package lv.helloit.test;

import lv.helloit.test.tasks.Task;
import lv.helloit.test.tasks.TaskService;
import lv.helloit.test.users.User;
import lv.helloit.test.users.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);

        TaskService ts = context.getBean(TaskService.class);
        ts.addTask(new Task(null, "task 1", "description", null));
        ts.addTask(new Task(null, "task 2", "description", null));
        ts.addTask(new Task(null, "task 3", "description", null));
        ts.addTask(new Task(null, "task 4", "description", null));
        ts.addTask(new Task(null, "task 5", "description", null));

        UserService us = context.getBean(UserService.class);
        User u = new User();
        u.name = "User Name";
        u.lastName = "User Last Name";
        u.age = 123;
        us.add(u);
	}

}
