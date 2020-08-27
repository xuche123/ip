import java.util.Scanner;
import java.util.Arrays;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }
}

public class Duke {

    public static void main(String[] args) {
        String command;
        Task[] list = new Task[100];
        int itemCount = 0;


        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        command = in.nextLine();

        while  (!command.equals("bye")) {

            if (!command.equals("list") && !command.contains("done")) {
                System.out.println("added: " + command + "\n");
                Task item = new Task(command);
                list[itemCount] = item;
                itemCount++;
            }

            else if (command.contains("done")) {
                int taskNum = Integer.parseInt(command.substring(5));
                list[taskNum-1].setDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[\u2713] " + list[taskNum-1].getDescription());
                System.out.println("\n");
            }

            else {
                System.out.println("\n");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println((i+1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
                System.out.println("\n");
            }
            command = in.nextLine();
        }

        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
