package jukebox;

import jukebox.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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
        // Null entries would make list(), search, and persistence fail later and obscure the cause.
        assert task != null : "task list cannot contain null tasks";
        tasks.add(task);
    }

    /**
     * Returns all tasks formatted as a numbered list.
     *
     * @return Numbered string representation of all tasks.
     */
    public String list() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s%n", i + 1, tasks.get(i)))
                .reduce("", String::concat);
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
     * Checks whether a task with the given description exists.
     *
     * @param description Description to search for.
     * @return True if a matching task exists.
     */
    public boolean containsTask(String description) {
        return tasks.stream().anyMatch(task -> task.getDetails().equals(description));
    }

    private boolean areValid(int... indices) {
        if (indices == null || indices.length == 0) {
            return false;
        }
        for (int idx : indices) {
            if (idx < 1 || idx > tasks.size()) {
                return false;
            }
        }
        // All callers below convert these 1-based values to safe 0-based positions.
        assert indices.length > 0 && Arrays.stream(indices).allMatch(i -> i >= 1 && i <= tasks.size());
        return true;
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
     * Marks every task at the given indices as done.
     *
     * @param indices Tasks to mark done.
     * @return Is successful.
     */
    public boolean markTasks(int... indices) {
        if (!areValid(indices)) {
            return false;
        }
        for (int idx : indices) {
            tasks.get(idx - 1).markDone();
        }
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
     * Marks all tasks at given indices as undone.
     *
     * @param indices Tasks to mark as undone.
     * @return Is successful.
     */
    public boolean unmarkTasks(int... indices) {
        if (!areValid(indices)) {
            return false;
        }
        for (int idx : indices) {
            tasks.get(idx - 1).unmarkDone();
        }
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

    /**
     * Removes tasks at the given indices.
     *
     * @param indices Tasks to remove.
     * @return Whether the requested tasks were removed successfully.
     */
    public boolean removeTasks(int... indices) {
        if (!areValid(indices)) {
            return false;
        }
        Set<Integer> uniqueIndices = new HashSet<>();
        for (int idx : indices) {
            uniqueIndices.add(idx - 1);
        }
        Integer[] sorted = uniqueIndices.toArray(new Integer[0]);
        Arrays.sort(sorted, java.util.Comparator.reverseOrder());
        for (int idx : sorted) {
            tasks.remove((int) idx);
        }
        return true;
    }

}
