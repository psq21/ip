package jukebox.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void saveFormat_unmarked() {
        ToDo td = new ToDo("Complete project");
        assertEquals("T | 0 | Complete project", td.saveFormat());
    }

    @Test
    public void saveFormat_marked() {
        ToDo td = new ToDo("Complete project");
        td.markDone();
        assertEquals("T | 1 | Complete project", td.saveFormat());
    }

    @Test
    public void toString_unmarked() {
        ToDo td = new ToDo("Complete project");
        assertEquals("[T][ ] Complete project", td.toString());
    }

    @Test
    public void toString_marked() {
        ToDo td = new ToDo("Complete project");
        td.markDone();
        assertEquals("[T][X] Complete project", td.toString());
    }
}
