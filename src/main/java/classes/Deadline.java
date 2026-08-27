package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String stringBy;
    protected LocalDate dateBy = null;

    public Deadline(String details, String by) {
        super(details);
        try {
            LocalDate date = LocalDate.parse(by);
            this.dateBy = date;
        } catch (DateTimeParseException e) {
            this.stringBy = by;
        }
    }

    protected String byString() {
        return dateBy != null
                ? dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : stringBy;
    }

    @Override
    public String saveFormat() {
        return String.format("D | %d | %s | %s",
                done ? 1 : 0,
                getDetails(),
                byString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byString() + ")";
    }
}
