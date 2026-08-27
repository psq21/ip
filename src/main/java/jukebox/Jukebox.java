package jukebox;

import java.util.Scanner;


public class Jukebox {
    private static TaskList tasks = new TaskList();

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

    private static void chat(Scanner sc) {
        String inp = sc.nextLine();
        Action action = Action.fromInput(inp);

        switch (action) {
            case BYE -> UI.exit();
            case LIST -> tasks.list();
            case MARK -> UI.handleMark(inp, tasks);
            case UNMARK -> UI.handleUnmark(inp, tasks);
            case TODO -> UI.handleTodo(inp, tasks);
            case DEADLINE -> UI.handleDeadline(inp, tasks);
            case EVENT -> UI.handleEvent(inp, tasks);
            case DELETE -> UI.handleDelete(inp, tasks);
            default -> System.out.println("eeek?? nani ??/");
        }
    }

    public static void main(String[] args) {
        String chatbotName = "jukebox";
        Scanner sc = new Scanner(System.in);
        // Personality: uwu
        String greeting = String.format("Hoi hoi im %s nice to meet you :333", chatbotName);
        System.out.println(greeting);
        Storage.loadData(tasks);
        while (true) {
            chat(sc);
        }
    }
}
