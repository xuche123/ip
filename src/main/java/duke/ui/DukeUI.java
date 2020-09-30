package duke.ui;

public class DukeUI {
    public static final String horizLine = "____________________________________________________________";

    public static void printAcknowledgement() {
        printMessageWithBorders("Got it. I've added this task: ");
    }

    public static void showFarewellMessage() {
        printMessageWithBorders("Bye. Hope to see you again soon!");
    }

    public static void showWelcomeMessage() {
        printMessageWithBorders("Hello! I'm Duke.\n" + "What can I do for you?");
    }

    public static void printMessageWithBorders(String message) {
        printHorizLine();
        System.out.println(message);
        printHorizLine();
    }

    public static void printHorizLine() {
        System.out.println(horizLine);
    }
}
