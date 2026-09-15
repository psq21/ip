package uwu;

import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.Task;
import uwu.task.ToDo;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.stream.Collectors;

/**
 * Handles loading and saving tasks in the application's data file.
 */
public class Storage {
    private static final String DEFAULT_TASK_DATA_FOLDER = "data";
    private static final String STORAGE_FOLDER_PROPERTY = "uwu.storage.folder";
    private static final String FIELD_SEPARATOR_REGEX = "\\s*\\|\\s*";
    private static final String EVENT_TIME_SEPARATOR_REGEX = "\\s+to\\s+";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String DONE_STATUS = "1";

    private static File taskDataFolder() {
        return new File(System.getProperty(STORAGE_FOLDER_PROPERTY, DEFAULT_TASK_DATA_FOLDER));
    }

    private static File taskDataFile() {
        return new File(taskDataFolder(), "tasks.txt");
    }

    /**
     * Sets up data storage file for usage.
     *
     * @throws IOException If failed to create file/directory.
     */
    public static void useTaskFile() throws IOException {
        File dataFolder = taskDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new IOException();
            }
        }

        File dataFile = taskDataFile();
        if (!dataFile.exists()) {
            if (!dataFile.createNewFile()) {
                throw new IOException();
            }
        }
    }

    /**
     * Writes a task to disk.
     *
     * @param task Task to write.
     * @return Is successful.
     */
    public static boolean saveData(Task task) {
        try {
            useTaskFile();
            FileWriter fw = new FileWriter(taskDataFile(), true);
            fw.write(task.saveFormat() + System.lineSeparator());
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Writes all to disk.
     * Wipes current data in file.
     *
     * @param tasks Tasks to write.
     * @return Is successful.
     */
    public static boolean rewriteData(TaskList tasks) {
        try {
            useTaskFile();
            FileWriter fw = new FileWriter(taskDataFile());
            String data = tasks.getTasks().stream()
                    .map(Task::saveFormat)
                    .collect(Collectors.joining(System.lineSeparator()));
            if (!data.isEmpty()) {
                data += System.lineSeparator();
            }
            fw.write(data);
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads all tasks from file.
     *
     * @param tasks List to load to.
     * @return Is successful.
     */
    public static boolean loadData(TaskList tasks) {
        try {
            useTaskFile();
            File f = taskDataFile();
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    return false;
                }
            }

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FIELD_SEPARATOR_REGEX, -1);
                if (fields.length < 3) continue;

                String taskType = fields[0].trim();
                boolean isDone = fields[1].trim().equals(DONE_STATUS);
                Task task;

                switch (taskType) {
                    case TODO_TYPE:
                        task = new ToDo(fields[2].trim());
                        break;
                    case DEADLINE_TYPE:
                        if (fields.length < 4) continue;
                        task = new Deadline(fields[2].trim(), fields[3].trim());
                        break;
                    case EVENT_TYPE:
                        if (fields.length < 4) continue;
                        String[] times = fields[3].trim().split(EVENT_TIME_SEPARATOR_REGEX, 2);
                        if (times.length < 2) continue;
                        task = new Event(fields[2].trim(), times[0], times[1]);
                        break;
                    default:
                        continue;
                }

                // Each recognized record must produce exactly one concrete task before insertion.
                assert task != null : "task must be created";
                if (isDone) {
                    task.markDone();
                }
                tasks.add(task);
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
