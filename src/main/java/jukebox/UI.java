package jukebox;

import jukebox.task.Deadline;
import jukebox.task.Event;
import jukebox.task.Task;
import jukebox.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;

import static jukebox.Parser.*;
import static jukebox.Storage.saveAllData;
import static jukebox.Storage.saveData;

public class UI {

    public static void handleMark(String inp, TaskList tasks) {
        Integer idx = parseIndex(inp);
        if (idx != null) {
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
        Integer idx = parseIndex(inp);
        if (idx != null) {
            if (tasks.unmarkTask(idx)) {
                System.out.printf("unmarked item %d :PPP%n", idx);
            } else {
                System.out.println("oh...no..waaaaa *cries invalid twask nwumber....");
            }
            saveAllData(tasks);
        }
    }

    public static void handleTodo(String inp, TaskList tasks) {
        String details = parseTodo(inp);
        if (details != null) {
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
        String[] properties = parseDeadline(inp);
        try {
            if (properties != null) {
                Task newTask = new Deadline(properties[0], properties[1]);
                tasks.add(newTask);
                saveData(newTask);
                System.out.printf("oh no scary deadlinw.... %s%n", newTask);
            } else {
                // if appropriate arguments are not given
                System.out.println("no pls gib the details and the deadline");
            }
        } catch (Exception e) { // occurs if date is not in correct format
            System.out.println("pwease gib by in correct format? pweety pwease? (yyyy-MM-dd)");
        }
    }

    public static void handleEvent(String inp, TaskList tasks) {
        String[] properties = parseEvent(inp);
        if (properties != null) {
            try {
                Task newTask = new Event(properties[0], properties[1], properties[2]);
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
        Integer idx = parseIndex(inp);
        if (idx != null) {
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
