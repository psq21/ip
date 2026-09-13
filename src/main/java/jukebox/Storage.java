package jukebox;

import jukebox.task.Deadline;
import jukebox.task.Event;
import jukebox.task.Task;
import jukebox.task.ToDo;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Handles loading and saving tasks in the application's data file.
 */
public class Storage {
    private static final String TASK_DATA_FOLDER = "data";
    private static final String TASK_DATA_FILE = "data/tasks.txt";
    private static final String FIELD_SEPARATOR_REGEX = "\\s*\\|\\s*";
    private static final String EVENT_TIME_SEPARATOR_REGEX = "\\s+to\\s+";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String DONE_STATUS = "1";

    /**
     * Sets up data storage file for usage.
     *
     * @throws IOException If failed to create file/directory.
     */
    public static void useTaskFile() throws IOException {
        File dataFolder = new File(TASK_DATA_FOLDER);
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new IOException();
            }
        }

        File dataFile = new File(TASK_DATA_FILE);
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
            FileWriter fw = new FileWriter(TASK_DATA_FILE, true);
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
            FileWriter fw = new FileWriter(TASK_DATA_FILE);
            for (Task task : tasks.getTasks()) {
                fw.write(task.saveFormat() + System.lineSeparator());
            }
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
            File f = new File(TASK_DATA_FILE);
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
