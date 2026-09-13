package jukebox.task;

import java.time.LocalDate;

/**
 * Deadline class.
 * Subclass of Task.
 */
public class Deadline extends Task {
    private static final String SAVE_TYPE = "D";
    protected LocalDate by;

    /**
     * Constructor of a deadline task.
     *
     * @param details Details of task.
     * @param by Deadline of task.
     */
    public Deadline(String details, String by) {
        super(details);
        this.by = fromStringFormat(by);
    }

    @Override
    public String saveFormat() {
        return String.format("%s | %d | %s | %s",
                SAVE_TYPE,
                isDone ? DONE_STATUS : NOT_DONE_STATUS,
                getDetails(),
                toSaveDateFormat(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toOtherDateFormat(by) + ")";
    }
}
