import classes.Task;
import classes.Deadline;
import classes.ToDo;
import classes.Event;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import java.util.Scanner;

public class Jukebox {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

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
        // Personality: uwu
        String greeting = String.format("Hoi hoi im %s nice to meet you :333", chatbotName);
        System.out.println(greeting);
        while (true) {
            echo(sc);
        }
    }

    private static void addToTasks(Task task) {
        tasks.add(task);
    }

    private static void exit() {
        System.out.println("gwooooooooddbyyeee seeeeee youuuuuuuu <3");
        System.exit(0);
    }

    private static void echo(Scanner sc) {
        String inp = sc.nextLine();
        if (inp.equals("bye")) {
            Jukebox.exit();
        } else if (inp.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        } else if (inp.startsWith("mark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                if (idx > tasks.size() || idx < 0) {
                    System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
                }
                tasks.get(idx).markDone();
                System.out.println(String.format("marked item %d :D", idx));
            } else {
                System.out.println("pls gib task no. for me to mark uwu uwu");
            }
        } else if (inp.startsWith("unmark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                tasks.get(idx).unmarkDone();
            }
        } else if (inp.startsWith("todo")) {
            Matcher todoMatcher = TODO_PATTERN.matcher(inp);
            if (todoMatcher.matches()) {
                String details = todoMatcher.group(1);
                Task newTask = new ToDo(details);
                addToTasks(newTask);
                System.out.println(String.format("watashi added the task %s !! anata have %d tasks to go!!",
                        newTask, tasks.size()));
            } else {
                System.out.println("gib me something to work with !!! :(((");
            }
        } else if (inp.startsWith("deadline")) {
            Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(inp);
            if (deadlineMatcher.matches()) {
                String details = deadlineMatcher.group(1);
                String by = deadlineMatcher.group(2);
                Task newTask = new Deadline(details, by);
                addToTasks(newTask);
                System.out.println(String.format("oh no scary deadlinw.... %s", newTask));
            } else {
                System.out.println("no pls gib the details and the deadline");
            }
        } else if (inp.startsWith("event")) {
            Matcher eventMatcher = EVENT_PATTERN.matcher(inp);
            if (eventMatcher.matches()) {
                String details = eventMatcher.group(1);
                String from = eventMatcher.group(2);
                String to = eventMatcher.group(3);
                Task newTask = new Event(details, from, to);
                addToTasks(newTask);
                System.out.println(String.format("yeeeeees event %s added", newTask));
            } else {
                System.out.println("nu !!!! gimme the deets the from the to");
            }
        } else if (inp.startsWith("delete")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                if (idx > tasks.size() || idx < 0) {
                    System.out.println("inwalid index");
                } else {
                    tasks.remove(idx);
                    System.out.println("!!! begone you normie!!");
                }
            } else {
                System.out.println("wub wub... no indewx....");
            }
        } else {
            System.out.println("eeek?? nani ??/");
        }
    }
}
