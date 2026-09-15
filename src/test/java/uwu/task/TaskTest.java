package uwu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    private static final class TestTask extends Task {
        TestTask(String details) {
            super(details);
        }

        @Override
        public String saveFormat() {
            return getDetails();
        }
    }

    @Test
    void taskDefaultsToUnmarkedAndStoresDetails() {
        Task task = new TestTask("read notes");

        assertEquals("read notes", task.getDetails());
        assertEquals("[ ] read notes", task.toString());
    }

    @Test
    void taskCanBeMarkedAndUnmarked() {
        Task task = new TestTask("read notes");

        task.markDone();
        assertTrue(task.toString().contains("[X]"));

        task.unmarkDone();
        assertEquals("[ ] read notes", task.toString());
    }

    @Test
    void undatedTaskHasNoSortDateAndSupportsSubstringSearch() {
        Task task = new TestTask("read lecture notes");

        assertEquals(null, task.getSortDate());
        assertTrue(task.contains("lecture"));
        assertFalse(task.contains("tutorial"));
    }
}
