package classes;

public class Task {
    private boolean done = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.done ? "[X]" : "[ ]", details);
    }
}
