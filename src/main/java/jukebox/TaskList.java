package jukebox;

import jukebox.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean markTask(int idx) {
        idx = idx - 1;
        if (idx > tasks.size() || idx < 0) {
            return false;
        }
        tasks.get(idx).markDone();
        return true;
    }

    public boolean unmarkTask(int idx) {
        idx = idx - 1;
        if (idx > tasks.size() || idx < 0) {
            return false;
        }
        tasks.get(idx).unmarkDone();
        return true;
    }

    public boolean removeTask(int idx) {
        idx = idx - 1;
        if (idx >= tasks.size() || idx < 0) {
            return false;
        }
        tasks.remove(idx);
        return true;
    }
}
