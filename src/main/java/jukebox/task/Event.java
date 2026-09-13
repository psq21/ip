package jukebox.task;

import java.time.LocalDate;

/**
 * Represents a task occurring between a start date and an end date.
 */
public class Event extends Task {
    private static final String SAVE_TYPE = "E";
    private static final String TIME_SEPARATOR = " to ";
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor of an event task.
     *
     * @param details Details of task.
     * @param start Start date/time of task.
     * @param end End date/time of task.
     */
    public Event(String details, String start, String end) {
        super(details);
        this.start = fromStringFormat(start);
        this.end = fromStringFormat(end);
        // Event formatting and persistence require both endpoints to exist.
        assert this.start != null && this.end != null : "event endpoints must be parsed";
    }

    @Override
    public String saveFormat() {
        return String.format("%s | %d | %s | %s%s%s",
                SAVE_TYPE,
                this.isDone ? DONE_STATUS : NOT_DONE_STATUS,
                this.getDetails(),
                toOtherDateFormat(start),
                TIME_SEPARATOR,
                toOtherDateFormat(end));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                toOtherDateFormat(start),
                toOtherDateFormat(end));
    }
}
