import java.util.Scanner;

public class Jukebox {
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
        } else {
            System.out.println(inp);
        }
    }
}
