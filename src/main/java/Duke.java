import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String command;
        String[] list = new String[100];
        int itemCount = 0;
        int listNum = 1;

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        command = in.nextLine();

        while  (!command.equals("bye")) {

            if (!command.equals("list")) {
                System.out.println("added: " + command + "\n");
                list[itemCount] = command;
                itemCount++;
            }
            else {
                String[] printedList = Arrays.copyOf(list, itemCount);
                for (int i = 0; i < printedList.length; i++) {
                    System.out.println((listNum++) + ". " + printedList[i]);
                }
                System.out.println("\n");
                listNum = 1;
            }
            command = in.nextLine();
        }

        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
