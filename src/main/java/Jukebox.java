import classes.Task;
import classes.Deadline;
import classes.ToDo;
import classes.Event;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Jukebox {
    private static String TASKDATAFILE = "data/tasks.txt";
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

    enum Action {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Action fromInput(String inp) {
            if (inp.equals("bye")) return BYE;
            if (inp.equals("list")) return LIST;
            if (inp.startsWith("mark")) return MARK;
            if (inp.startsWith("unmark")) return UNMARK;
            if (inp.startsWith("todo")) return TODO;
            if (inp.startsWith("deadline")) return DEADLINE;
            if (inp.startsWith("event")) return EVENT;
            if (inp.startsWith("delete")) return DELETE;
            return UNKNOWN;
        }
    }

    public static void main(String[] args) {
        String chatbotName = "jukebox";
        Scanner sc = new Scanner(System.in);
        // Personality: uwu
        String greeting = String.format("Hoi hoi im %s nice to meet you :333", chatbotName);
        System.out.println(greeting);
        loadData();
        while (true) {
            chat(sc);
        }
    }

    private static void addToTasks(Task task) {
        tasks.add(task);
    }

    private static void exit() {
        System.out.println("gwooooooooddbyyeee seeeeee youuuuuuuu <3");
        System.exit(0);
    }

    private static void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }

    private static void handleMark(String inp) {
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
    }

    private static void handleUnmark(String inp) {
        Scanner s = new Scanner(inp);
        s.next();
        if (s.hasNextInt()) {
            int idx = s.nextInt();
            tasks.get(idx).unmarkDone();
        }
    }

    private static void handleTodo(String inp) {
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
    }

    private static void handleDeadline(String inp) {
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
    }

    private static void handleEvent(String inp) {
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
    }

    public static void handleDelete(String inp) {
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
    }

    private static void chat(Scanner sc) {
        String inp = sc.nextLine();
        Action action = Action.fromInput(inp);

        switch (action) {
            case BYE -> exit();
            case LIST -> list();
            case MARK -> handleMark(inp);
            case UNMARK -> handleUnmark(inp);
            case TODO -> handleTodo(inp);
            case DEADLINE -> handleDeadline(inp);
            case EVENT -> handleEvent(inp);
            case DELETE -> handleDelete(inp);
            default -> System.out.println("eeek?? nani ??/");
        }
    }

    private static void saveData(Task task) {
        try {
            FileWriter fw = new FileWriter(TASKDATAFILE, true);
            fw.write(task.saveFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving to disk :(((((");
        }
    }

    private static void loadData() {
        File f = new File(TASKDATAFILE);
        if (!f.exists()) {
            
        }
    }
}
