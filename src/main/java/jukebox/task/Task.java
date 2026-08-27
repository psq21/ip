package jukebox.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class Task {
    protected boolean done = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    abstract public String saveFormat();

    protected String toSaveDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    protected String toOtherDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.done ? "[X]" : "[ ]", details);
    }
}
