import classes.Task;
import classes.Deadline;
import classes.ToDo;
import classes.Event;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Scanner;

public class Jukebox {
    private static Task[] tasks = new Task[100];
    private static int taskNo = 0;

    // todo {details}
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)$");

    // deadline {details} /by {string}
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(.+)$");

    // event {details} /from {string} /to {string}
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    public static void main(String[] args) {
        String chatbotName = "jukebox";
        Scanner sc = new Scanner(System.in);
        // Personality: weirdo
        String greeting = String.format("Hoi hoi im %s nice to meet you whaddya need", chatbotName);
        System.out.println(greeting);
        while (true) {
            echo(sc);
        }
    }

    private static int addToTasks(Task task) {
        tasks[taskNo] = task;
        taskNo++;
        return taskNo;
    }

    private static void exit() {
        System.out.println("gooooooooddbyyeee seeeeee youuuuuuuu");
        System.exit(0);
    }

    private static void echo(Scanner sc) {
        String inp = sc.nextLine();
        if (inp.equals("bye")) {
            Jukebox.exit();
        } else if (inp.equals("list")) {
            for (int i = 0; i < taskNo; i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks[i]));
            }
        } else if (inp.startsWith("mark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                tasks[idx].markDone();
                System.out.println(String.format("marked item %d :D", idx));
            }
        } else if (inp.startsWith("unmark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                tasks[idx].unmarkDone();
            }
        } else if (TODO_PATTERN.matcher(inp).matches()) {
            Matcher todoMatcher = TODO_PATTERN.matcher(inp);
            todoMatcher.matches();
            String details = todoMatcher.group(1);
            Task newTask = new ToDo(details);
            addToTasks(newTask);
            System.out.println(String.format("i added the task %s !! you have %d tasks to go!!",
                    newTask, taskNo));
        } else if (DEADLINE_PATTERN.matcher(inp).matches()) {
            Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(inp);
            deadlineMatcher.matches();
            String details = deadlineMatcher.group(1);
            String by = deadlineMatcher.group(2);
            Task newTask = new Deadline(details, by);
            addToTasks(newTask);
            System.out.println(String.format("oh no scary deadlinw.... %s", newTask));
        } else if (EVENT_PATTERN.matcher(inp).matches()) {
            Matcher eventMatcher = EVENT_PATTERN.matcher(inp);
            eventMatcher.matches();
            String details = eventMatcher.group(1);
            String from = eventMatcher.group(2);
            String to = eventMatcher.group(3);
            Task newTask = new Event(details, from, to);
            addToTasks(newTask);
            System.out.println(String.format("yeah boi event %s added", newTask));
        } else {
            System.out.println("no idea what you talkin about man talk again");
        }
    }
}
