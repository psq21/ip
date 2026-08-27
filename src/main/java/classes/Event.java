package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String stringStart;
    protected String stringEnd;
    protected LocalDate dateStart = null;
    protected LocalDate dateEnd = null;

    public Event(String details, String start, String end) {
        super(details);
        try {
            LocalDate date = LocalDate.parse(start);
            this.dateStart = date;
        } catch (DateTimeParseException e) {
            this.stringStart = start;
        }
        try {
            LocalDate date = LocalDate.parse(end);
            this.dateEnd = date;
        } catch (DateTimeParseException e) {
            this.stringEnd = end;
        }
    }

    protected String startString() {
        return dateStart != null
                ? dateStart.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : stringStart;
    }

    protected String endString() {
        return dateEnd != null
                ? dateEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : stringEnd;
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s to %s",
                this.done ? 1 : 0,
                this.getDetails(),
                startString(),
                endString());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startString(), endString());
    }
}
