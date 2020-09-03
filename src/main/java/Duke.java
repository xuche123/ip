import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {

        Task[] tasks = new Task[100];
        boolean isFinished = false;
        String horizLine = "____________________________________________________________";
        showWelcomeMessage(horizLine);
        executeUserCommand(tasks, horizLine);

    }

    private static void executeUserCommand(Task[] tasks, String horizLine) {

        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        int taskCount = 0;

        while (!command.equals("bye")) {
            if (command.contains("todo")) {
                System.out.println(horizLine);
                String description = command.substring(5);
                System.out.println("Got it. I've added this task: ");
                tasks[taskCount] = new Todo(description);
                System.out.println(tasks[taskCount].toString());
                System.out.println("There are now " + taskCount + " tasks in the list.");
                System.out.println(horizLine);
                taskCount = taskCount + 1;
            }

            else if (command.contains("deadline")) {
                System.out.println(horizLine);
                int dividerPos = command.indexOf('/');
                String description = command.substring(9, dividerPos);
                String by = command.substring(dividerPos + 4);
                System.out.println("Got it. I've added this task: ");
                tasks[taskCount] = new Deadline(description, by);
                System.out.println(tasks[taskCount].toString());
                taskCount++;
                System.out.println("There are now " + taskCount + " tasks in the list.");
                System.out.println(horizLine);
            }

            else if (command.contains("event")){
                System.out.println(horizLine);
                int dividerPos = command.indexOf('/');
                String description = command.substring(6, dividerPos);
                String at = command.substring(dividerPos + 4);
                System.out.println("Got it. I've added this task: ");
                tasks[taskCount] = new Event(description, at);
                System.out.println(tasks[taskCount].toString());
                taskCount++;
                System.out.println("There are now " + taskCount + " tasks in the list.");
                System.out.println(horizLine);
            }

            else if (command.equals("list")) {
                System.out.println(horizLine);
                System.out.println("Here are the tasks on your list: ");
                for (int t = 0; t < taskCount; t++) {
                    System.out.println((t+1) + ". " + tasks[t].toString());
                }
                System.out.println(horizLine);
            }

            else if (command.contains("done")) {
                int taskNum = Integer.parseInt(command.substring(5));
                tasks[taskNum-1].setDone();
                System.out.println(horizLine);
                System.out.println("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks[taskNum-1].getDescription());

                System.out.println(horizLine);
            }

            else {
                System.out.println(horizLine);
                System.out.println("Got it. I've added this task: " + command);
                taskCount++;
                System.out.println("There are now " + taskCount + " tasks in the list.");
                System.out.println(horizLine);
                tasks[taskCount] = new Task(command);
            }

            command = in.nextLine();
        }

        showFarewellMessage(horizLine);
    }

    private static void showFarewellMessage(String horizLine) {

        System.out.println(horizLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizLine);

    }

    private static void showWelcomeMessage(String horizLine) {

        System.out.println(horizLine);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(horizLine);

    }
}
