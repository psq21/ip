import classes.Task;

import java.util.Scanner;

public class Jukebox {
    private static Task[] tasks = new Task[100];
    private static int taskNo = 0;

    public static void main(String[] args) {
        String chatbotName = "jukebox";
        Scanner sc = new Scanner(System.in);
        // Personality: weirdo
        String greeting = String.format("Hoi hoi im %s nice to meet you whaddya need", chatbotName);
        System.out.println(greeting);
        while (true) {
            echo(sc);
        }
    }

    private static void exit() {
        System.out.println("gooooooooddbyyeee seeeeee youuuuuuuu");
        System.exit(0);
    }

    private static void echo(Scanner sc) {
        String inp = sc.nextLine();
        if (inp.equals("bye")) {
            Jukebox.exit();
        } else if (inp.equals("list")) {
            for (int i = 0; i < taskNo; i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks[i]));
            }
        } else if (inp.startsWith("mark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                tasks[idx].markDone();
                System.out.println(String.format("marked item %d :D", idx));
            }
        } else if (inp.startsWith("unmark")) {
            Scanner s = new Scanner(inp);
            s.next();
            if (s.hasNextInt()) {
                int idx = s.nextInt();
                tasks[idx].unmarkDone();
            }
        } else {
            Task newTask = new Task(inp);
            tasks[taskNo] = newTask;
            taskNo++;
            System.out.println(String.format("i added '%s' for you :3 ", inp));
        }
    }
}
