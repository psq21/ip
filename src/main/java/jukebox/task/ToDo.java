package jukebox.task;

/**
 * ToDo class.
 * Subclass of Task.
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo task.
     *
     * @param details Details of task.
     */
    public ToDo(String details) {
        super(details);
    }

    @Override
    public String saveFormat() {
        return String.format("T | %d | %s",
                this.isDone ? 1 : 0,
                this.getDetails());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
