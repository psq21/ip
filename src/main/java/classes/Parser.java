package classes;

import java.util.regex.Pattern;

public class Parser { // to do {details}
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)$");

    // deadline {details} /by {string}
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(.+)$");

    // event {details} /from {string} /to {string}
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    public static void parse(String inp) {

    }
}
