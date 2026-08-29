package jukebox.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void deadline_exceptionThrown() {
       try {
           Deadline d = new Deadline("CA1 Essay", "1");
           fail();
       } catch (DateTimeParseException e) {
           assertEquals("Text '1' could not be parsed at index 0", e.getMessage());
       } catch (Exception e) {
           fail();
       }
    }

    @Test
    public void deadline_success() {
        try {
            Deadline d = new Deadline("CA1 Essay", "2026-10-10");
            assertEquals("[D][ ] CA1 Essay (by: Oct 10 2026)", d.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
