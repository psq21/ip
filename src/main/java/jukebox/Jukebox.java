package jukebox;

/**
 * Main class for chatbot.
 */
public class Jukebox {
    private static TaskList tasks = new TaskList();

    protected enum Action {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;

        public static Action fromInput(String inp) {
            if (inp.equals("bye")) {
                return BYE;
            }
            if (inp.equals("list")) {
                return LIST;
            }
            if (inp.startsWith("mark")) {
                return MARK;
            }
            if (inp.startsWith("unmark")) {
                return UNMARK;
            }
            if (inp.startsWith("todo")) {
                return TODO;
            }
            if (inp.startsWith("deadline")) {
                return DEADLINE;
            }
            if (inp.startsWith("event")) {
                return EVENT;
            }
            if (inp.startsWith("delete")) {
                return DELETE;
            }
            if (inp.startsWith("find")) {
                return FIND;
            }
            return UNKNOWN;
        }
    }

    public static String getResponse(String inp) {
        Action action = Action.fromInput(inp);

        return switch (action) {
            case BYE -> UI.exit();
            case LIST -> tasks.list();
            case MARK -> UI.handleMark(inp, tasks);
            case UNMARK -> UI.handleUnmark(inp, tasks);
            case TODO -> UI.handleTodo(inp, tasks);
            case DEADLINE -> UI.handleDeadline(inp, tasks);
            case EVENT -> UI.handleEvent(inp, tasks);
            case DELETE -> UI.handleDelete(inp, tasks);
            case FIND -> UI.handleFind(inp, tasks);
            default -> ("eeek?? nani ??/");
        };
    }

    public static void main(String[] args) {
        String chatbotName = "jukebox";
        // Personality: uwu
        String greeting = String.format("Hoi hoi im %s nice to meet you :333", chatbotName);
        System.out.println(greeting);
        Storage.loadData(tasks);
    }
}
