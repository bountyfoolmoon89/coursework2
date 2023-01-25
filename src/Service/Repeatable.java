package Service;

import java.time.LocalDateTime;

public interface Repeatable {

    boolean checkOccurrance (LocalDateTime localDateTime);

    void setTitle(String title);

    LocalDateTime getDateTime();


}
