package classes;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s to %s",
                            this.done ? 1 : 0,
                            this.getDetails(),
                            this.start,
                            this.end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
