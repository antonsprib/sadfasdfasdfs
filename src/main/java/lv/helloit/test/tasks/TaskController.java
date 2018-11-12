package lv.helloit.test.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Collection<Task> get() {
        LOGGER.info("Get user request");
        return taskService.get();
    }

    @PostMapping
    public void create(@RequestBody Task t) {
        taskService.addTask(t);
    }

    @PutMapping
    public void assign(@RequestParam Long taskId, @RequestParam Long userId) {
        taskService.assign(taskId, userId);
    }
}