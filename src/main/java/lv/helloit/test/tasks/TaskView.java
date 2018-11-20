package lv.helloit.test.tasks;

import java.util.Objects;

public class TaskView extends Task {
    private String username;

    public TaskView() {
    }

    public TaskView(String username) {
        this.username = username;
    }

    public TaskView(Long id, String title, String description, Long assignedUserId, String username) {
        super(id, title, description, assignedUserId);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TaskView taskView = (TaskView) o;
        return Objects.equals(username, taskView.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username);
    }

    @Override
    public String toString() {
        return "TaskView{}";
    }
}
