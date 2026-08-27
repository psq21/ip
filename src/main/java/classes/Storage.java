package classes;

import java.io.*;

public class Storage {
    private static final String TASKDATAFOLDER = "data";
    private static final String TASKDATAFILE = "data/tasks.txt";

    public static void useTaskFile() throws IOException {
        File dataFolder = new File(TASKDATAFOLDER);
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new IOException();
            }
        }

        File dataFile = new File(TASKDATAFILE);
        if (!dataFile.exists()) {
            if (!dataFile.createNewFile()) {
                throw new IOException();
            }
        }
    }

    public static void saveData(Task task) {
        try {
            useTaskFile();
            FileWriter fw = new FileWriter(TASKDATAFILE, true);
            fw.write(task.saveFormat() + System.lineSeparator());
            fw.close();
            System.out.println("saved to dis");
        } catch (IOException e) {
            System.out.println("Swomething went wrong when saving to disk :(((((");
        }
    }

    public static void saveAllData(TaskList tasks) {
        try {
            useTaskFile();
            FileWriter fw = new FileWriter(TASKDATAFILE);
            for (Task task : tasks.getTasks()) {
                fw.write(task.saveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Swomething went wrong when saving to disk :(((((");
        }
    }

    public static void loadData(TaskList tasks) {
        try {
            useTaskFile();
            File f = new File(TASKDATAFILE);
            if (!f.exists()) {
                if (f.createNewFile()) {
                    return;
                } else {
                    System.out.println("i has twouble mwaking fwile...");
                }
            }

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\s*\\|\\s*", -1);
                if (fields.length < 3) continue;

                String taskType = fields[0].trim();
                boolean isDone = fields[1].trim().equals("1");
                Task task;

                switch (taskType) {
                    case "T":
                        task = new ToDo(fields[2].trim());
                        break;
                    case "D":
                        if (fields.length < 4) continue;
                        task = new Deadline(fields[2].trim(), fields[3].trim());
                        break;
                    case "E":
                        if (fields.length < 4) continue;
                        String[] times = fields[3].trim().split("\\s+to\\s+", 2);
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

        } catch (IOException e) {
            System.out.println("Somethign wen weong when loding to dis");
        }
    }
}
