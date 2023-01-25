package Tasks;
import Service.Repeatable;
import Service.TaskType;
import java.time.LocalDateTime;

public class DailyTasks extends Task implements Repeatable {


    public DailyTasks(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean checkOccurrance(LocalDateTime requestedDate) {
        return getDateTime().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
