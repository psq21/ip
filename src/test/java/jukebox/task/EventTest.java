package jukebox.task;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void saveFormat_unmarked_success() {
        Event e = new Event("Math class", "2100-11-11", "2100-11-20");
        assertEquals("E | 0 | Math class | Nov 11 2100 to Nov 20 2100", e.saveFormat());
    }

    @Test
    public void saveFormat_marked_success() {
        Event e = new Event("Math class", "2100-11-11", "2100-11-20");
        e.markDone();
        assertEquals("E | 1 | Math class | Nov 11 2100 to Nov 20 2100", e.saveFormat());
    }

    @Test
    public void toString_unmarked_success() {
        Event e = new Event("Math class", "2100-11-11", "2100-11-20");
        assertEquals("[E][ ] Math class (from: Nov 11 2100 to: Nov 20 2100)",
                e.toString());
    }

    @Test
    public void toString_marked_success() {
        Event e = new Event("Math class", "2100-11-11", "2100-11-20");
        e.markDone();
        assertEquals("[E][X] Math class (from: Nov 11 2100 to: Nov 20 2100)",
                e.toString());
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        assertThrows(DateTimeParseException.class,
                () -> new Event("Math class", "1", "2100-11-20"));
        assertThrows(DateTimeParseException.class,
                () -> new Event("Math class", "2100-11-20", "1"));
    }

    @Test
    public void constructor_invalidCalendarDate_throwsException() {
        assertThrows(DateTimeParseException.class,
                () -> new Event("CA1 Essay", "2026-02-29", "2000-10-10"));
        assertThrows(DateTimeParseException.class,
                () -> new Event("CA1 Essay", "2000-10-10", "2026-02-40"));
    }

    @Test
    public void constructor_validLeapDay_success() {
        Event d = new Event("Math class", "2024-02-29", "2024-03-01");
        assertEquals("[E][ ] Math class (from: Feb 29 2024 to: Mar 1 2024)", d.toString());
        Event d1 = new Event("Math class", "2024-03-01", "2024-02-29");
        assertEquals("[E][ ] Math class (from: Mar 1 2024 to: Feb 29 2024)", d1.toString());
    }
}
