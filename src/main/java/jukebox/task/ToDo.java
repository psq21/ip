package jukebox.task;

/**
 * ToDo class.
 * Subclass of Task.
 */
public class ToDo extends Task {
    private static final String SAVE_TYPE = "T";

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
        return String.format("%s | %d | %s",
                SAVE_TYPE,
                this.isDone ? DONE_STATUS : NOT_DONE_STATUS,
                this.getDetails());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
