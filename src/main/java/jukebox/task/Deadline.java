package jukebox.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String details, String by) {
        super(details);
        this.by = fromStringFormat(by);
    }

    @Override
    public String saveFormat() {
        return String.format("D | %d | %s | %s",
                done ? 1 : 0,
                getDetails(),
                toSaveDateFormat(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toOtherDateFormat(by) + ")";
    }
}
