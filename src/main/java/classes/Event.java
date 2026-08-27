package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String details, String start, String end) {
        super(details);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateStart = LocalDate.parse(start, formatter);
        LocalDate dateEnd = LocalDate.parse(end, formatter);
        this.start = dateStart;
        this.end = dateEnd;
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s to %s",
                this.done ? 1 : 0,
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
