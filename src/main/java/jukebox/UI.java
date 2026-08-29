package jukebox;

import jukebox.task.Deadline;
import jukebox.task.Event;
import jukebox.task.Task;
import jukebox.task.ToDo;

import java.time.format.DateTimeParseException;

import static jukebox.Parser.*;

/**
 * Class to handle input by user.
 */
public class UI {

    /**
     * Marks the corresponding task at index as done.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleMark(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.markTask(idx)) {
                System.out.printf("marked item %d :D%n", idx);
            } else {
                System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
            }
            UI.rewriteData(tasks);
        } else {
            System.out.println("pls gib task no. for me to mark uwu uwu");
        }
    }

    /**
     * Marks the corresponding task at index as undone.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleUnmark(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.unmarkTask(idx)) {
                System.out.printf("unmarked item %d :PPP%n", idx);
            } else {
                System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
            }
            UI.rewriteData(tasks);
        }
    }

    /**
     * Adds new ToDo task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleTodo(String inp, TaskList tasks) {
        String details = parseTodo(inp);
        if (details != null) {
            Task newTask = new ToDo(details);
            tasks.add(newTask);
            UI.saveData(newTask);
            System.out.printf("watashi added the task %s !! anata have %d tasks to go!!%n",
                    newTask, tasks.size());
        } else {
            System.out.println("gib me something to work with !!! :(((");
        }
    }

    /**
     * Adds new Deadline task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleDeadline(String inp, TaskList tasks) {
        String[] properties = parseDeadline(inp);
        try {
            if (properties != null) {
                Task newTask = new Deadline(properties[0], properties[1]);
                tasks.add(newTask);
                UI.saveData(newTask);
                System.out.printf("oh no scary deadlinw.... %s%n", newTask);
            } else {
                // if appropriate arguments are not given
                System.out.println("no pls gib the details and the deadline");
            }
        } catch (Exception e) { // occurs if date is not in correct format
            System.out.println("pwease gib by in correct format? pweety pwease? (yyyy-MM-dd)");
        }
    }

    /**
     * Adds new Event task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleEvent(String inp, TaskList tasks) {
        String[] properties = parseEvent(inp);
        if (properties != null) {
            try {
                Task newTask = new Event(properties[0], properties[1], properties[2]);
                tasks.add(newTask);
                UI.saveData(newTask);
                System.out.printf("yeeeeees event %s added%n", newTask);
            } catch (DateTimeParseException e) {
                System.out.println("pwease gib start/end in correct format? pweety pwease? (yyyy-MM-dd)");
            }
        } else {
            System.out.println("nu !!!! gimme the deets the from the to");
        }
    }

    /**
     * Deletes task at corresponding index from list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleDelete(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.removeTask(idx)) {
                System.out.println("!!! begone you normie!!");
                UI.rewriteData(tasks);
            } else {
                System.out.println("inwalid index");
            }
        } else {
            System.out.println("wub wub... no indewx....");
        }
    }

    /**
     * Finds all tasks with given string.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static void handleFind(String inp, TaskList tasks) {
        String search = Parser.parseFind(inp);
        if (search != null) {
            System.out.println("here's all the matching stuffs :PP");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.contains(search)) {
                    System.out.printf("%d. %s %n", i + 1, task);
                }
            }
        } else {
            System.out.println("no mwatch :(");
        }
    }

    /**
     * Calls storage method to save a task to disk.
     *
     * @param task Task to be written.
     */
    public static void saveData(Task task) {
        if (Storage.saveData(task)) {
            System.out.println("saved to dis");
        } else {
            System.out.println("Swomething went wrong when saving to disk :(((((");
        }
    }

    /**
     * Calls storage method to save all tasks to disk.
     * Used for modifying existing tasks.
     *
     * @param tasks List of tasks.
     */
    public static void rewriteData(TaskList tasks) {
        if (Storage.rewriteData(tasks)) {
            System.out.println("saved to dis");
        } else {
            System.out.println("Swomething went wrong when saving to disk :(((((");
        }
    }

    /**
     * Calls storage method to load all data from disk.
     *
     * @param tasks List to add tasks to.
     */
    public static void loadData(TaskList tasks) {
        if (Storage.loadData(tasks)) {
            System.out.println("okiii loaded from disk :3");
        } else {
            System.out.println("Somethign wen weong when loding from dis");
        }
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("gwooooooooddbyyeee seeeeee youuuuuuuu <3");
        System.exit(0);
    }
}
