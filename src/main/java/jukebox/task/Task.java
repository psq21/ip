package jukebox.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents a task that can be completed and persisted.
 */
abstract public class Task {
    /** Date format used for task persistence and command input. */
    protected static final String DATE_FORMAT = "uuuu-MM-dd";

    /** Date format used when displaying tasks to the user. */
    protected static final String DISPLAY_DATE_FORMAT = "MMM d yyyy";

    /** Integer status values used by the task persistence format. */
    protected static final int NOT_DONE_STATUS = 0;
    protected static final int DONE_STATUS = 1;

    protected boolean isDone = false;
    private String details;

    protected Task(String details) {
        this.details = details;
        // Every task must have searchable/displayable details; callers validate user input first.
        assert details != null && !details.isBlank() : "task details must be non-blank";
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
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Formats date for printing.
     *
     * @param date Date to format.
     * @return Formatted string.
     */
    protected String toOtherDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT));
    }

    /**
     * Reads date from formatted string.
     *
     * @param dateString Formatted string.
     * @return LocalDate object.
     */
    protected LocalDate fromStringFormat(String dateString) {
        // Date strings reach this method only after a task command or save record supplies one.
        assert dateString != null && !dateString.isBlank() : "date string must be present";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
                                            .withResolverStyle(ResolverStyle.STRICT);
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.isDone ? "[X]" : "[ ]", details);
    }
}
