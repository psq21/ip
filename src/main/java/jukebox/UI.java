package jukebox;

import jukebox.task.Deadline;
import jukebox.task.Event;
import jukebox.task.Task;
import jukebox.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jukebox.Storage.saveAllData;
import static jukebox.Storage.saveData;

public class UI {

    // to do {details}
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)$");

    // deadline {details} /by {string}
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(.+)$");

    // event {details} /from {string} /to {string}
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    public static void handleMark(String inp, TaskList tasks) {
        Scanner s = new Scanner(inp);
        s.next();
        if (s.hasNextInt()) {
            int idx = s.nextInt();
            if (tasks.markTask(idx)) {
                System.out.printf("marked item %d :D%n", idx);
            } else {
                System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
            }
            saveAllData(tasks);
        } else {
            System.out.println("pls gib task no. for me to mark uwu uwu");
        }
    }

    public static void handleUnmark(String inp, TaskList tasks) {
        Scanner s = new Scanner(inp);
        s.next();
        if (s.hasNextInt()) {
            int idx = s.nextInt();
            if (tasks.unmarkTask(idx)) {
                System.out.printf("unmarked item %d :PPP%n", idx);
            } else {
                System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
            }
            saveAllData(tasks);
        }
    }

    public static void handleTodo(String inp, TaskList tasks) {
        Matcher todoMatcher = TODO_PATTERN.matcher(inp);
        if (todoMatcher.matches()) {
            String details = todoMatcher.group(1);
            Task newTask = new ToDo(details);
            tasks.add(newTask);
            saveData(newTask);
            System.out.printf("watashi added the task %s !! anata have %d tasks to go!!%n",
                    newTask, tasks.size());
        } else {
            System.out.println("gib me something to work with !!! :(((");
        }
    }

    public static void handleDeadline(String inp, TaskList tasks) {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(inp);
        if (deadlineMatcher.matches()) {
            try {
                String details = deadlineMatcher.group(1);
                String by = deadlineMatcher.group(2);
                Task newTask = new Deadline(details, by);
                tasks.add(newTask);
                saveData(newTask);
                System.out.printf("oh no scary deadlinw.... %s%n", newTask);
            } catch (DateTimeParseException e) {
                System.out.println("pwease gib by in correct format? pweety pwease? (yyyy-MM-dd)");
            }
        } else {
            System.out.println("no pls gib the details and the deadline");
        }
    }

    public static void handleEvent(String inp, TaskList tasks) {
        Matcher eventMatcher = EVENT_PATTERN.matcher(inp);
        if (eventMatcher.matches()) {
            try {
                String details = eventMatcher.group(1);
                String from = eventMatcher.group(2);
                String to = eventMatcher.group(3);
                Task newTask = new Event(details, from, to);
                tasks.add(newTask);
                saveData(newTask);
                System.out.printf("yeeeeees event %s added%n", newTask);
            } catch (DateTimeParseException e) {
                System.out.println("pwease gib start/end in correct format? pweety pwease? (yyyy-MM-dd)");
            }
        } else {
            System.out.println("nu !!!! gimme the deets the from the to");
        }
    }

    public static void handleDelete(String inp, TaskList tasks) {
        Scanner s = new Scanner(inp);
        s.next();
        if (s.hasNextInt()) {
            int idx = s.nextInt();
            if (tasks.removeTask(idx)) {
                System.out.println("!!! begone you normie!!");
            } else {
                System.out.println("inwalid index");
            }
        } else {
            System.out.println("wub wub... no indewx....");
        }
    }

    public static void exit() {
        System.out.println("gwooooooooddbyyeee seeeeee youuuuuuuu <3");
        System.exit(0);
    }
}
