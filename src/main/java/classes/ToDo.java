package classes;

public class ToDo extends Task {
    public ToDo(String details) {
        super(details);
    }

    @Override
    public String saveFormat() {
        return String.format("T | %d | %s",
                this.done ? 1 : 0,
                this.getDetails());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
