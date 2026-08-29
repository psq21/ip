package jukebox;

import jukebox.task.Task;
import jukebox.task.ToDo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // to do {details}
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)$");

    // deadline {details} /by {string}
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(.+)$");

    // event {details} /from {string} /to {string}
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    public static Integer parseIndex(String inp) {
        Scanner s = new Scanner(inp);
        s.next();
        if (s.hasNextInt()) {
            int idx = s.nextInt();
            return idx;
        } else {
            return null;
        }
    }

    public static String parseTodo(String inp) {
        Matcher todoMatcher = TODO_PATTERN.matcher(inp);
        if (todoMatcher.matches()) {
            return todoMatcher.group(1);
        } else {
            return null;
        }
    }

    public static String[] parseDeadline(String inp) {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(inp);
        if (deadlineMatcher.matches()) {
            String details = deadlineMatcher.group(1);
            String by = deadlineMatcher.group(2);
            return new String[]{details, by};
        } else {
            return null;
        }
    }

    public static String[] parseEvent(String inp) {
        Matcher eventMatcher = EVENT_PATTERN.matcher(inp);
        if (eventMatcher.matches()) {
            String details = eventMatcher.group(1);
            String from = eventMatcher.group(2);
            String to = eventMatcher.group(3);
            return new String[] {details, from, to};
        } else {
            return null;
        }
    }
}
