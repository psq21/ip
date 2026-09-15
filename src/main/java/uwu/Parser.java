package uwu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to extract data from commands.
 */
public class Parser {
    // to do {details}
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)$");

    // find {string}
    private static final Pattern FIND_PATTERN =
            Pattern.compile("^find\\s+(.+)$");

    // deadline {details} /by {string}
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(.+)$");

    // event {details} /from {string} /to {string}
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    /**
     * Returns integer passed as input to the command.
     * Used for mark, unmark and delete.
     *
     * @param inp Command input by user.
     * @return Index | null.
     */
    public static Integer parseIndex(String inp) {
        int[] indices = parseIndices(inp);
        return indices == null || indices.length != 1 ? null : indices[0];
    }

    /**
     * Returns all integer indices passed to a command. The command must be
     * followed by one or more whitespace-separated integers; any other token
     * makes the whole input invalid.
     *
     * @param inp Command input by user.
     * @return Indices, or null when the arguments are missing or malformed.
     */
    public static int[] parseIndices(String inp) {
        if (inp == null) {
            return null;
        }
        String[] parts = inp.trim().split("\\s+");
        if (parts.length < 2) {
            return null;
        }
        List<Integer> indices = new ArrayList<>();
        try {
            for (int i = 1; i < parts.length; i++) {
                indices.add(Integer.parseInt(parts[i]));
            }
        } catch (NumberFormatException e) {
            return null;
        }
        int[] result = indices.stream().mapToInt(Integer::intValue).toArray();
        // The successful parse must preserve one integer for every argument token.
        assert result.length == parts.length - 1 : "parsed index count must match input arguments";
        return result;
    }

    /**
     * Returns details passed as input to todo command.
     *
     * @param inp Command input by user.
     * @return Details of ToDo | null.
     */
    public static String parseTodo(String inp) {
        Matcher todoMatcher = TODO_PATTERN.matcher(inp);
        if (todoMatcher.matches()) {
            return todoMatcher.group(1).trim();
        } else {
            return null;
        }
    }

    /**
     * Returns details and deadline passed to deadline command.
     *
     * @param inp Command input by user.
     * @return Details and deadline of Deadline | null.
     */
    public static String[] parseDeadline(String inp) {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(inp);
        if (deadlineMatcher.matches()) {
            String details = deadlineMatcher.group(1).trim();
            String by = deadlineMatcher.group(2).trim();
            return new String[]{details, by};
        } else {
            return null;
        }
    }

    /**
     * Returns details, start and end passed to event command.
     *
     * @param inp Command input by user.
     * @return Details, start & end of Event | null.
     */
    public static String[] parseEvent(String inp) {
        Matcher eventMatcher = EVENT_PATTERN.matcher(inp);
        if (eventMatcher.matches()) {
            String details = eventMatcher.group(1).trim();
            String from = eventMatcher.group(2).trim();
            String to = eventMatcher.group(3).trim();
            return new String[] {details, from, to};
        } else {
            return null;
        }
    }

    /**
     * Returns string to search for.
     *
     * @param inp Command input by user.
     * @return String to search for.
     */
    public static String parseFind(String inp) {
        Matcher matcher = FIND_PATTERN.matcher(inp);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
