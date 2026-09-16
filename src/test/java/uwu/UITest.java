package uwu;

import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

    private TaskList tasks;
    private ByteArrayOutputStream output;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        output = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void handleTodo_validInput() {
        String response = UI.handleTodo("todo buy milk", tasks);

        assertEquals(1, tasks.size());
        assertEquals("buy milk", tasks.getTasks().get(0).getDetails());
        assertTrue(response.contains("buy milk"));
    }

    @Test
    void handleTodo_invalidInput() {
        String response = UI.handleTodo("todo", tasks);

        assertEquals(0, tasks.size());
        assertTrue(response.contains("gib me something"));
    }

    @Test
    void handleDeadline_validInput() {
        UI.handleDeadline("deadline submit report /by 2026-09-01", tasks);

        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTasks().get(0));
        assertEquals("submit report", tasks.getTasks().get(0).getDetails());
    }

    @Test
    void handleDeadline_invalidInput() {
        String response = UI.handleDeadline("deadline submit report /by tomorrow", tasks);

        assertEquals(0, tasks.size());
        assertTrue(response.contains("yyyy-MM-dd"));
    }

    @Test
    void handleEvent_validInput() {
        UI.handleEvent("event meeting /from 2026-09-01 /to 2026-09-02", tasks);

        assertEquals(1, tasks.size());
        assertInstanceOf(Event.class, tasks.getTasks().get(0));
        assertEquals("meeting", tasks.getTasks().get(0).getDetails());
    }

    @Test
    void handleEvent_invalidInput() {
        String response = UI.handleEvent("event meeting /from 2026-09-01", tasks);

        assertEquals(0, tasks.size());
        assertTrue(response.contains("gimme the deets"));
    }

    @Test
    void handleMark_validIndex() {
        tasks.add(new ToDo("buy milk"));

        String response = UI.handleMark("mark 1", tasks);

        assertEquals("[T][X] buy milk", tasks.getTasks().get(0).toString());
        assertTrue(response.contains("marked item 1"));
    }

    @Test
    void handleMark_invalidIndex() {
        tasks.add(new ToDo("buy milk"));

        String response = UI.handleMark("mark 2", tasks);

        assertEquals("[T][ ] buy milk", tasks.getTasks().get(0).toString());
        assertTrue(response.contains("invalid"));
    }

    @Test
    void handleMark_nonNumericIndex() {
        tasks.add(new ToDo("buy milk"));

        String response = UI.handleMark("mark abc", tasks);

        assertEquals("[T][ ] buy milk", tasks.getTasks().get(0).toString());
        assertTrue(response.contains("task no."));
    }

    @Test
    void handleUnmark_validIndex() {
        ToDo task = new ToDo("buy milk");
        task.markDone();
        tasks.add(task);

        UI.handleUnmark("unmark 1", tasks);

        assertEquals("[T][ ] buy milk", tasks.getTasks().get(0).toString());
    }

    @Test
    void handleDelete_validIndex() {
        tasks.add(new ToDo("first"));
        tasks.add(new ToDo("second"));

        UI.handleDelete("delete 1", tasks);

        assertEquals(1, tasks.size());
        assertEquals("second", tasks.getTasks().get(0).getDetails());
    }

    @Test
    void handleDelete_invalidIndex() {
        tasks.add(new ToDo("first"));

        String response = UI.handleDelete("delete 2", tasks);

        assertEquals(1, tasks.size());
        assertTrue(response.contains("inwalid index"));
    }

    @Test
    void handleDelete_nonNumericIndex() {
        tasks.add(new ToDo("first"));

        String response = UI.handleDelete("delete abc", tasks);

        assertEquals(1, tasks.size());
        assertTrue(response.contains("no indewx"));
    }

    @Test
    void list() {
        tasks.add(new ToDo("first"));
        tasks.add(new ToDo("second"));

        assertEquals("1. [T][ ] first\n2. [T][ ] second\n", tasks.list());
    }

    @Test
    void handleFind_validInputWithTrailingSpace() {
        tasks.add(new ToDo("buy milk"));
        tasks.add(new ToDo("read book"));

        String response = UI.handleFind("find milk ", tasks);

        assertTrue(response.contains("buy milk"));
        assertFalse(response.contains("read book"));
    }
}
