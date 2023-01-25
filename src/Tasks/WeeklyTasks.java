package Tasks;

import Service.Repeatable;
import Service.TaskType;

import java.time.LocalDateTime;

public class WeeklyTasks extends Task implements Repeatable {
    public WeeklyTasks(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean checkOccurrance(LocalDateTime requestedDate) {
        return getDateTime().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }
}
