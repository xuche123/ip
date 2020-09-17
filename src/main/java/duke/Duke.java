package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import file.FileManager;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {
    public static final String horizLine = "____________________________________________________________";

    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        showWelcomeMessage();
        FileManager.loadFile(tasks);
        executeUserCommand(tasks);
    }

    private static void executeUserCommand(ArrayList<Task> tasks) throws IOException {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().startsWith("todo")) {
                computeTodoCommand(tasks, command);
            } else if (command.toLowerCase().startsWith("deadline")) {
                computeDeadlineCommand(tasks, command);
            } else if (command.toLowerCase().startsWith("event")){
                computeEventCommand(tasks, command);
            } else if (command.toLowerCase().equals("list")) {
                printList(tasks);
            } else if (command.toLowerCase().startsWith("done")) {
                markAsDone(tasks, command);
            } else if (command.toLowerCase().startsWith("delete")) {
                removeTask(tasks, command);
            } else {
                printErrorMessage("Invalid command given. Please enter a new command.");
            }
            FileManager.writeToFile(tasks);
            command = in.nextLine();
        }
        showFarewellMessage();
    }

    private static void markAsDone(ArrayList<Task> tasks, String command) {
        int taskNum = -1;
        boolean proceedOnwards = false;
        try {
            taskNum = Integer.parseInt(command.substring(5));
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            printErrorMessage("Please provide a valid number.");
        }

        if (proceedOnwards) {
            try {
                tasks.get(taskNum - 1).setDone();
                System.out.println(Duke.horizLine);
                System.out.println("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks.get(taskNum - 1).getDescription());
                System.out.println(Duke.horizLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                printErrorMessage("Number given is not in the list.");
            } catch (IndexOutOfBoundsException e) {
                printErrorMessage("Please provide a valid number.");
            }
        }
    }

    private static void printList(ArrayList<Task> tasks) {
        System.out.println(Duke.horizLine);
        System.out.println("Here are the tasks on your list: ");
        for (int i = 0; i < tasks.size() ; i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }
        System.out.println(Duke.horizLine);
    }

    private static void computeEventCommand(ArrayList<Task> tasks, String command) {
        String[] eventDetails = new String[2];
        try {
            extractCommandDetails(eventDetails, command, "Event");
            printAcknowledgement();
            tasks.add(new Event(eventDetails[0], eventDetails[1]));
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() + " tasks in the list.");
            System.out.println(Duke.horizLine);
        } catch (StringIndexOutOfBoundsException e) {
            printErrorMessage("Description for an Event cannot be left empty.");
        }
    }

    private static void computeDeadlineCommand(ArrayList<Task> tasks, String command) {
        String[] deadlineDetails = new String[2];
        try {
            extractCommandDetails(deadlineDetails, command, "Deadline");
            printAcknowledgement();
            tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() +  " tasks in the list.");
            System.out.println(Duke.horizLine);
        } catch (StringIndexOutOfBoundsException e) {
            printErrorMessage("Description for a Deadline cannot be left empty.");
        }
    }

    private static void extractCommandDetails(String[] commandDetails, String command, String commandType)
            throws StringIndexOutOfBoundsException{
        int dividerPos = command.indexOf('/');
        commandDetails[0] = command.substring((commandType.equals("Deadline") ? 9 : 6), dividerPos);
        commandDetails[1] = command.substring(dividerPos + 4);
    }

    private static void computeTodoCommand(ArrayList<Task> tasks, String command) {
        try {
            String description = command.substring(5);
            printAcknowledgement();
            tasks.add(new Todo(description));
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() + " tasks in the list.");
            System.out.println(Duke.horizLine);
        } catch (StringIndexOutOfBoundsException e) {
            printErrorMessage("Description for a Todo cannot be left empty.");
        }
    }

    private static void removeTask(ArrayList<Task> tasks, String command) {
        int indexToDelete = -1;
        boolean proceedOnwards = false;
        try {
            indexToDelete = Integer.parseInt(command.substring(7));
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            printErrorMessage("Please provide a valid number.");
        }

        if (proceedOnwards) {
            try {
                String toDelete = tasks.get(indexToDelete).toString();
                tasks.remove(indexToDelete);
                System.out.println("Noted. I've removed this task");
                System.out.println(toDelete);
                System.out.println(Duke.horizLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                printErrorMessage("Number given is not in the list.");
            } catch (IndexOutOfBoundsException e) {
                printErrorMessage("Please provide a valid number.");
            }
        }
    }

    private static void printAcknowledgement() {
        System.out.println(Duke.horizLine);
        System.out.println("Got it. I've added this task: ");
    }


    private static void showFarewellMessage() {
        System.out.println(Duke.horizLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.horizLine);
    }

    private static void showWelcomeMessage() {
        System.out.println(Duke.horizLine);
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
        System.out.println(Duke.horizLine);
    }

    private static void printErrorMessage(String message) {
        System.out.println(Duke.horizLine);
        System.out.println(message);
        System.out.println(Duke.horizLine);
    }

}