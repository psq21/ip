public class Jukebox {
    public static void main(String[] args) {
        String chatbotName = "jukebox";
        // Personality: weirdo
        String greeting = String.format("Hoi hoi im %s nice to meet you whaddya need", chatbotName);
        System.out.println(greeting);
        Jukebox.exit();
    }

    public static void exit() {
        System.out.println("gooooooooddbyyeee seeeeee youuuuuuuu");
    }
}
