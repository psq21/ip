package uwu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.ToDo;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    @TempDir
    private Path tempDirectory;
    private String originalUserDirectory;
    private String originalStorageFolder;

    @BeforeEach
    void setUp() {
        originalStorageFolder = System.getProperty("uwu.storage.folder");
        System.setProperty("uwu.storage.folder", tempDirectory.resolve("data").toString());
    }

    @AfterEach
    void tearDown() {
        if (originalStorageFolder == null) {
            System.clearProperty("uwu.storage.folder");
        } else {
            System.setProperty("uwu.storage.folder", originalStorageFolder);
        }
    }

    private Path taskFile() {
        return tempDirectory.resolve("data/tasks.txt");
    }

    @Test
    void saveData_appendsTaskToFile() throws Exception {
        assertTrue(Storage.saveData(new ToDo("buy milk")));
        assertTrue(Storage.saveData(new Deadline("submit report", "2026-09-20")));

        assertEquals("T | 0 | buy milk\nD | 0 | submit report | 2026-09-20\n",
                Files.readString(taskFile()));
    }

    @Test
    void rewriteData_replacesExistingContents() throws Exception {
        Files.createDirectories(tempDirectory.resolve("data"));
        Files.writeString(taskFile(), "old data\n");
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("first"));
        tasks.add(new Event("meeting", "2026-09-21", "2026-09-22"));

        assertTrue(Storage.rewriteData(tasks));
        assertEquals("T | 0 | first\nE | 0 | meeting | Sep 21 2026 to Sep 22 2026\n",
                Files.readString(taskFile()));
    }

    @Test
    void loadData_recreatesTaskTypesAndDoneStatus() throws Exception {
        Files.createDirectories(tempDirectory.resolve("data"));
        Files.writeString(taskFile(),
                "T | 0 | buy milk\n"
                        + "D | 1 | submit report | 2026-09-20\n"
                        + "E | 0 | meeting | 2026-09-21 to 2026-09-22\n");
        TaskList tasks = new TaskList();

        assertTrue(Storage.loadData(tasks));
        assertEquals(3, tasks.size());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
        assertEquals("[D][X] submit report (by: Sep 20 2026)", tasks.get(1).toString());
    }

}
