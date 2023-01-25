package Calendar;

import Service.Repeatable;
import Service.TaskType;
import Tasks.*;
import Validates.ValidateUtils;

import java.lang.invoke.WrongMethodTypeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MyCalendar {

    public static final Map<Integer, Repeatable> actualTasks = new HashMap<>();

    public static void inputTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.print("Введите название задачи: ");
            String title = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Опишите задачу: ");
            String description = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Выберите тип задачи: 0 - Рабочая, 1 - Личная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Выберите периодичность задачи: 0 - Однократная, 1- Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy. HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (WrongMethodTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repeatable task = null;
            try {
                task = createTask(occurance, title, description, taskType, eventDate);
                System.out.println("Задача создана " + task);
            } catch (WrongMethodTypeException e) {
                System.out.println(e.getMessage());
            }

        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.MM.yyyy HH:mm!");
            createEvent(scanner, title, description, taskType, occurance);
        }
    }
    private static Repeatable createTask (int occurance, String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongMethodTypeException {
        return switch (occurance) {
            case 0 -> {
                OncelyTasks oncelyTasks = new OncelyTasks(title, description, taskType, localDateTime);
                actualTasks.put(oncelyTasks.getId(), oncelyTasks);
                yield oncelyTasks;
            }
            case 1 -> {
                DailyTasks dailyTasks = new DailyTasks(title, description, taskType, localDateTime);
                actualTasks.put(dailyTasks.getId(), dailyTasks);
                yield dailyTasks;
            }
            case 2 -> {
                WeeklyTasks weeklyTasks = new WeeklyTasks(title, description, taskType, localDateTime);
                actualTasks.put(weeklyTasks.getId(), weeklyTasks);
                yield weeklyTasks;
            }
            case 3 -> {
                MonthlyTasks monthlyTasks = new MonthlyTasks(title, description, taskType, localDateTime);
                actualTasks.put(monthlyTasks.getId(), monthlyTasks);
                yield monthlyTasks;
            }
            case 4 -> {
                YearlyTasks yearlyTasks = new YearlyTasks(title, description, taskType, localDateTime);
                actualTasks.put(yearlyTasks.getId(), yearlyTasks);
                yield yearlyTasks;
            }
            default -> null;
        };
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату в виде dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.MM.yyyy HH:mm!");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }

    private static List<Repeatable> findTasksByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task : actualTasks.values()) {
            if (task.checkOccurrance(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

}
