package lv.helloit.test.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Collection<Task> get() {
        LOGGER.info("Get task request");
        return taskService.get();
    }

    @GetMapping(value = "/{id}")
    public Optional<Task> getById(@PathVariable Long id) {
        return taskService.get(id);
    }

    @PostMapping
    public void create(@RequestBody Task t) {
        taskService.addTask(t);
    }

    @DeleteMapping
    public boolean delete(@RequestParam(value = "taskId") Long id) {
        return taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);

        return taskService.update(task);
    }

    @PutMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean assign(@RequestParam(name = "taskId") Long taskId, @RequestParam Long userId) {
        return taskService.assign(taskId, userId);
    }
}