package jukebox.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Task class.
 */
abstract public class Task {
    protected boolean isDone = false;
    private String details;

    protected Task(String details) {
        this.details = details;
    }

    /**
     * Returns details of task.
     *
     * @return Details of task.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Checks if details contains given string.
     *
     * @param str String to search for.
     * @return Details contains search string.
     */
    public boolean contains(String str) {
        return this.details.contains(str);
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns data of task formatted for saving.
     *
     * @return Formatted string.
     */
    abstract public String saveFormat();

    /**
     * Formats date for saving.
     *
     * @param date Date to format.
     * @return Formatted string.
     */
    protected String toSaveDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }

    /**
     * Formats date for printing.
     *
     * @param date Date to format.
     * @return Formatted string.
     */
    protected String toOtherDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Reads date from formatted string.
     *
     * @param dateString Formatted string.
     * @return LocalDate object.
     */
    protected LocalDate fromStringFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                                            .withResolverStyle(ResolverStyle.STRICT);
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.isDone ? "[X]" : "[ ]", details);
    }
}
