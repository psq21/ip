package jukebox;

import jukebox.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskListTest {
    @Test
    void addTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("first"));
        tasks.add(new ToDo("second"));

        assertEquals(2, tasks.size());
        assertEquals("first", tasks.getTasks().get(0).getDetails());
    }

    @Test
    void markUnmarkTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("first"));

        assertTrue(tasks.markTask(1));
        assertEquals("[T][X] first", tasks.getTasks().get(0).toString());
        assertTrue(tasks.unmarkTask(1));
        assertEquals("[T][ ] first", tasks.getTasks().get(0).toString());
    }

    @Test
    void invalidIndices() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("first"));

        assertFalse(tasks.markTask(0));
        assertFalse(tasks.markTask(2));
        assertFalse(tasks.unmarkTask(2));
        assertFalse(tasks.removeTask(0));
        assertFalse(tasks.removeTask(2));
        assertEquals(1, tasks.size());
    }

    @Test
    void removeTask() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("first"));
        tasks.add(new ToDo("second"));

        assertTrue(tasks.removeTask(1));
        assertEquals("second", tasks.getTasks().get(0).getDetails());
    }
}
