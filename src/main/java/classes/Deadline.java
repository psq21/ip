package classes;

public class Deadline extends Task {
    protected String by;

    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    @Override
    public String saveFormat() {
        return String.format("D | %d | %s | %s",
                this.done ? 1 : 0,
                this.getDetails(),
                this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
