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
    public static String handleMark(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.markTask(idx)) {
                UI.rewriteData(tasks);
                return String.format("marked item %d :D%n", idx);
            } else {
                return "oh...no..waaaaa *cries invalid twask nwumber....";
            }
        } else {
            return "pls gib task no. for me to mark uwu uwu";
        }
    }

    /**
     * Marks the corresponding task at index as undone.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleUnmark(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.unmarkTask(idx)) {
                UI.rewriteData(tasks);
                return String.format("unmarked item %d :PPP%n", idx);
            } else {
                return "oh...no..waaaaa *cries invalid twask nwumber....";
            }
        }
        return "pls gib task no. for me to unmark uwu uwu";
    }

    /**
     * Adds new ToDo task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleTodo(String inp, TaskList tasks) {
        String details = parseTodo(inp);
        if (details != null) {
            Task newTask = new ToDo(details);
            tasks.add(newTask);
            UI.saveData(newTask);
            return String.format("watashi added the task %s !! anata have %d tasks to go!!%n",
                    newTask, tasks.size());
        } else {
            return "gib me something to work with !!! :(((";
        }
    }

    /**
     * Adds new Deadline task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleDeadline(String inp, TaskList tasks) {
        String[] properties = parseDeadline(inp);
        try {
            if (properties != null) {
                Task newTask = new Deadline(properties[0], properties[1]);
                tasks.add(newTask);
                UI.saveData(newTask);
                return String.format("oh no scary deadlinw.... %s%n", newTask);
            } else {
                // if appropriate arguments are not given
                return "no pls gib the details and the deadline";
            }
        } catch (Exception e) { // occurs if date is not in correct format
            return "pwease gib by in correct format? pweety pwease? (yyyy-MM-dd)";
        }
    }

    /**
     * Adds new Event task to task list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleEvent(String inp, TaskList tasks) {
        String[] properties = parseEvent(inp);
        if (properties != null) {
            try {
                Task newTask = new Event(properties[0], properties[1], properties[2]);
                tasks.add(newTask);
                UI.saveData(newTask);
                return String.format("yeeeeees event %s added%n", newTask);
            } catch (DateTimeParseException e) {
                return "pwease gib start/end in correct format? pweety pwease? (yyyy-MM-dd)";
            }
        } else {
            return "nu !!!! gimme the deets the from the to";
        }
    }

    /**
     * Deletes task at corresponding index from list.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleDelete(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.removeTask(idx)) {
                UI.rewriteData(tasks);
                return "!!! begone you normie!!";
            } else {
                return "inwalid index";
            }
        } else {
            return "wub wub... no indewx....";
        }
    }

    /**
     * Finds all tasks with given string.
     *
     * @param inp Command input by user.
     * @param tasks List of tasks.
     */
    public static String handleFind(String inp, TaskList tasks) {
        String search = Parser.parseFind(inp);
        StringBuilder res = new StringBuilder("here's all the matching stuffs :PP%n");
        if (search != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.contains(search)) {
                    res.append(String.format("%d. %s %n", i + 1, task));
                }
            }
            return res.toString();
        } else {
            return "no mwatch :(";
        }
    }

    /**
     * Calls storage method to save a task to disk.
     *
     * @param task Task to be written.
     */
    public static String saveData(Task task) {
        if (Storage.saveData(task)) {
            return "saved to dis";
        } else {
            return "Swomething went wrong when saving to disk :(((((";
        }
    }

    /**
     * Calls storage method to save all tasks to disk.
     * Used for modifying existing tasks.
     *
     * @param tasks List of tasks.
     */
    public static String rewriteData(TaskList tasks) {
        if (Storage.rewriteData(tasks)) {
            return "saved to dis";
        } else {
            return "Swomething went wrong when saving to disk :(((((";
        }
    }

    /**
     * Calls storage method to load all data from disk.
     *
     * @param tasks List to add tasks to.
     */
    public static String loadData(TaskList tasks) {
        if (Storage.loadData(tasks)) {
            return "okiii loaded from disk :3";
        } else {
            return "Somethign wen weong when loding from dis";
        }
    }

    /**
     * Exits the program.
     */
    public static String exit() {
        return "gwooooooooddbyyeee seeeeee youuuuuuuu <3";
    }
}
