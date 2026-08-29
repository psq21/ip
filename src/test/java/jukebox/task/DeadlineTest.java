package jukebox.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void saveFormat_unmarked_success() {
        Deadline d = new Deadline("CA1 Essay", "2100-10-10");
        assertEquals("D | 0 | CA1 Essay | 2100-10-10", d.saveFormat());
    }

    @Test
    public void saveFormat_marked_success() {
        Deadline d = new Deadline("CA1 Essay", "2100-10-10");
        d.markDone();
        assertEquals("D | 1 | CA1 Essay | 2100-10-10", d.saveFormat());
    }

    @Test
    public void toString_unmarked_success() {
        Deadline d = new Deadline("CA1 Essay", "2000-10-10");
        assertEquals("[D][ ] CA1 Essay (by: Oct 10 2000)", d.toString());
    }

    @Test
    public void toString_marked_success() {
        Deadline d = new Deadline("CA1 Essay", "2000-10-01");
        d.markDone();
        assertEquals("[D][X] CA1 Essay (by: Oct 1 2000)", d.toString());
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class,
                () -> new Deadline("CA1 Essay", "1"));
        assertEquals("Text '1' could not be parsed at index 0", exception.getMessage());
    }

    @Test
    public void constructor_invalidCalendarDate_throwsException() {
        assertThrows(DateTimeParseException.class,
                () -> new Deadline("CA1 Essay", "2026-02-29"));
    }

    @Test
    public void constructor_validLeapDay_success() {
        Deadline d = new Deadline("CA1 Essay", "2024-02-29");
        assertEquals("[D][ ] CA1 Essay (by: Feb 29 2024)", d.toString());
    }
}
