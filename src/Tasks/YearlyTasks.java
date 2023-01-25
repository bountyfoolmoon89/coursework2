package Tasks;

import Service.Repeatable;
import Service.TaskType;

import java.time.LocalDateTime;

public class YearlyTasks extends Task implements Repeatable {
    public YearlyTasks(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean checkOccurrance(LocalDateTime requestedDate) {
        return getDateTime().getYear() == requestedDate.getYear();
    }
}
