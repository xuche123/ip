import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String horizLine = "____________________________________________________________";
        showWelcomeMessage(horizLine);
        executeUserCommand(tasks, horizLine);
    }

    private static void executeUserCommand(Task[] tasks, String horizLine) {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        int taskCount = 0;

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().startsWith("todo")) {
                taskCount = computeTodoCommand(tasks, horizLine, command, taskCount);
            } else if (command.toLowerCase().startsWith("deadline")) {
                taskCount = computeDeadlineCommand(tasks, horizLine, command, taskCount);
            } else if (command.toLowerCase().startsWith("event")){
                taskCount = computeEventCommand(tasks, horizLine, command, taskCount);
            } else if (command.toLowerCase().equals("list")) {
                printList(tasks, horizLine, taskCount);
            } else if (command.toLowerCase().startsWith("done")) {
                markAsDone(tasks, horizLine, command);
            } else {
                System.out.println(horizLine);
                System.out.println("Invalid command given. Please enter a new command");
                System.out.println(horizLine);
            }
            command = in.nextLine();
        }
        showFarewellMessage(horizLine);
    }

    private static void markAsDone(Task[] tasks, String horizLine, String command) {
        int taskNum = -1;
        boolean proceedOnwards = false;
        try {
            taskNum = Integer.parseInt(command.substring(5));
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(horizLine);
            System.out.println("Please provide a valid number");
            System.out.println(horizLine);
        }
        if (proceedOnwards) {
            try {
                tasks[taskNum - 1].setDone();
                System.out.println(horizLine);
                System.out.println("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks[taskNum - 1].getDescription());
                System.out.println(horizLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(horizLine);
                System.out.println("Number given is not in the list.");
                System.out.println(horizLine);
            }
        }
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
        try {
            extractCommandDetails(eventDetails, command, "Event");
            printAcknowledgement(horizLine);
            tasks[taskCount] = new Event(eventDetails[0], eventDetails[1]);
            System.out.println(tasks[taskCount].toString());
            taskCount++;
            printListCount(horizLine, taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(horizLine);
            System.out.println("Description for an Event cannot be left empty");
            System.out.println(horizLine);
        }

        return taskCount;
    }

    private static int computeDeadlineCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        String[] deadlineDetails = new String[2];
        try {
            extractCommandDetails(deadlineDetails, command, "Deadline");
            printAcknowledgement(horizLine);
            tasks[taskCount] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            System.out.println(tasks[taskCount].toString());
            taskCount++;
            printListCount(horizLine, taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(horizLine);
            System.out.println("Description for a Deadline cannot be left empty");
            System.out.println(horizLine);
        }
        return taskCount;
    }

    private static void extractCommandDetails(String[] commandDetails, String command, String commandType) throws StringIndexOutOfBoundsException{
        int dividerPos = command.indexOf('/');
        commandDetails[0] = command.substring((commandType.equals("Deadline") ? 9 : 6), dividerPos);
        commandDetails[1] = command.substring(dividerPos + 4);
    }

    private static int computeTodoCommand(Task[] tasks, String horizLine, String command, int taskCount) {
        try {
            String description = command.substring(5);
            printAcknowledgement(horizLine);
            tasks[taskCount] = new Todo(description);
            System.out.println(tasks[taskCount].toString());
            taskCount++;
            printListCount(horizLine, taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(horizLine);
            System.out.println("Description for a Todo cannot be left empty");
            System.out.println(horizLine);
        }

        return taskCount;
    }

    private static void printAcknowledgement(String horizLine) {
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
