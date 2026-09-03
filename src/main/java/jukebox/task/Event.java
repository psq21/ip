package jukebox.task;

import java.time.LocalDate;

/**
 * Event class.
 * Subclass of Task.
 */
public class Event extends Task {
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
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s to %s",
                this.isDone ? 1 : 0,
                this.getDetails(),
                toOtherDateFormat(start),
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
