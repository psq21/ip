package jukebox;

import jukebox.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates data and functions associated with a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Adds given task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prints out all tasks.
     */
    public String list() {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            res.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
        }
        return res.toString();
    }

    /**
     * Returns size of list.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns task at given index.
     *
     * @param idx Index.
     * @return Task at given index.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Returns task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks task at given index as done.
     *
     * @param idx Index of task.
     * @return Is successful.
     */
    public boolean markTask(int idx) {
        idx = idx - 1;
        if (idx >= tasks.size() || idx < 0) {
            return false;
        }
        tasks.get(idx).markDone();
        return true;
    }

    /**
     * Marks task at given index as undone.
     *
     * @param idx Index of task.
     * @return Is successful.
     */
    public boolean unmarkTask(int idx) {
        idx = idx - 1;
        if (idx >= tasks.size() || idx < 0) {
            return false;
        }
        tasks.get(idx).unmarkDone();
        return true;
    }

    /**
     * Removes task at given index from list.
     *
     * @param idx Index of task.
     * @return Is successful.
     */
    public boolean removeTask(int idx) {
        idx = idx - 1;
        if (idx >= tasks.size() || idx < 0) {
            return false;
        }
        tasks.remove(idx);
        return true;
    }
}
