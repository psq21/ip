package uwu;

/**
 * Main class for chatbot.
 */
public class Uwu {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";
    private static TaskList tasks = new TaskList();

    protected enum Action {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, SORT, UNKNOWN;

        public static Action fromInput(String inp) {
            if (inp.equals(BYE_COMMAND)) {
                return BYE;
            }
            if (inp.equals(LIST_COMMAND)) {
                return LIST;
            }
            if (inp.startsWith(MARK_COMMAND)) {
                return MARK;
            }
            if (inp.startsWith(UNMARK_COMMAND)) {
                return UNMARK;
            }
            if (inp.startsWith(TODO_COMMAND)) {
                return TODO;
            }
            if (inp.startsWith(DEADLINE_COMMAND)) {
                return DEADLINE;
            }
            if (inp.startsWith(EVENT_COMMAND)) {
                return EVENT;
            }
            if (inp.startsWith(DELETE_COMMAND)) {
                return DELETE;
            }
            if (inp.startsWith(FIND_COMMAND)) {
                return FIND;
            }
            if (inp.equals(SORT_COMMAND)) {
                return SORT;
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
            case SORT -> UI.handleSort(tasks);
            default -> ("eeek?? nani ??/");
        };
    }

    public static void main(String[] args) {
        String chatbotName = "uwu";
        /*
        What is uwu? uwu also stylized UwU, is an emoticon representing a cute face.

        The "uwu" archetype is a prominent internet subculture and character trope
        rooted in cute, soft, and overly expressive aesthetics.

        Generally, replacing the letter 'r' with a 'w' to sound more cute is associated
        with this archetype.
         */
        String greeting = String.format("Hoi hoi im %s nice to meet you :333", chatbotName);
        System.out.println(greeting);
        Storage.loadData(tasks);
    }
}
