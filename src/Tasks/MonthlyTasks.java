package Tasks;

import Service.Repeatable;
import Service.TaskType;

import java.time.LocalDateTime;

public class MonthlyTasks extends Task implements Repeatable {
    public MonthlyTasks(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean checkOccurrance(LocalDateTime requestedDate) {
        return getDateTime().getMonth().equals(requestedDate.getMonth());
    }
}
