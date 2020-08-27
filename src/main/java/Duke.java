import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        command = in.nextLine();

        while  (!command.equals("bye")) {
            System.out.println(command + "\n");
            command = in.nextLine();
        }

        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
