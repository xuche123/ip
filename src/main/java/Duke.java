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
                taskCount = computeTodoCommand(tasks, horizLine, command, taskCount);
            }

            else if (command.contains("deadline")) {
                taskCount = computeDeadlineCommand(tasks, horizLine, command, taskCount);
            }

            else if (command.contains("event")){
                taskCount = computeEventCommand(tasks, horizLine, command, taskCount);
            }

            else if (command.equals("list")) {
                printList(tasks, horizLine, taskCount);
            }

            else if (command.contains("done")) {
                markAsDone(tasks, horizLine, command);
            }

            else {
                taskCount = computeBasicCommand(tasks, horizLine, command, taskCount);
            }

            command = in.nextLine();
        }

        showFarewellMessage(horizLine);
    }

    private static int computeBasicCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        printAcknowlegement(horizLine);
        tasks[taskCount] = new Task(command);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        printListCount(horizLine, taskCount);
        return taskCount;
    }

    private static void markAsDone(Task[] tasks, String horizLine, String command) {
        int taskNum = Integer.parseInt(command.substring(5));
        tasks[taskNum-1].setDone();
        System.out.println(horizLine);
        System.out.println("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks[taskNum-1].getDescription());
        System.out.println(horizLine);
    }

    private static void printList(Task[] tasks, String horizLine, int taskCount) {
        System.out.println(horizLine);
        System.out.println("Here are the tasks on your list: ");
        for (int t = 0; t < taskCount; t++) {
            System.out.println((t+1) + ". " + tasks[t].toString());
        }
        System.out.println(horizLine);
    }

    private static int computeEventCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        String[] eventDetails = new String[2];
        extractCommandDetails(eventDetails, command, "Event");
        printAcknowlegement(horizLine);
        tasks[taskCount] = new Event(eventDetails[0], eventDetails[1]);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        printListCount(horizLine, taskCount);
        return taskCount;
    }

    private static int computeDeadlineCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        String[] deadlineDetails = new String[2];
        extractCommandDetails(deadlineDetails, command, "Deadline");
        printAcknowlegement(horizLine);
        tasks[taskCount] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        printListCount(horizLine, taskCount);
        return taskCount;
    }

    private static void extractCommandDetails(String[] commandDetails, String command, String commandType) {
        int dividerPos = command.indexOf('/');
        commandDetails[0] = command.substring((commandType.equals("Deadline") ? 9 : 6), dividerPos);
        commandDetails[1] = command.substring(dividerPos + 4);
    }

    private static int computeTodoCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        String description = command.substring(5);
        printAcknowlegement(horizLine);
        tasks[taskCount] = new Todo(description);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        printListCount(horizLine, taskCount);
        return taskCount;
    }

    private static void printAcknowlegement(String horizLine) {
        System.out.println(horizLine);
        System.out.println("Got it. I've added this task: ");
    }

    private static void printListCount(String horizLine, int taskCount) {
        System.out.println("There are now " + taskCount + " tasks in the list.");
        System.out.println(horizLine);
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
