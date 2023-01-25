package Tasks;
import Service.TaskType;
import Validates.ValidateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String description;
    private TaskType taskType;
    public int id;
    public static int counter = 0;
    private LocalDateTime dateTime;

    public Task(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        this.title = ValidateUtils.validateString(title);
        this.description = ValidateUtils.validateString(description);
        this.taskType = taskType;
        this.dateTime = dateTime;
        counter++;
        id = counter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TaskType type) {
        this.taskType = taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && taskType == task.taskType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, taskType, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}
