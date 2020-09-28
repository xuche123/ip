package duke.parser;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.DukeUI;
import duke.file.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeParser {
    private static final int nineChar = 9;
    private static final int sevenChar = 7;
    private static final int sixChar = 6;
    private static final int fiveChar = 5;

    public static void executeUserCommand(ArrayList<Task> tasks) throws IOException {
        String command;
        String formattedCommand;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        formattedCommand = command.toLowerCase();

        while (!formattedCommand.equals("bye")) {
            if (formattedCommand.startsWith("todo")) {
                computeTodoCommand(tasks, command);
            } else if (formattedCommand.startsWith("deadline")) {
                computeDeadlineCommand(tasks, command);
            } else if (formattedCommand.startsWith("event")){
                computeEventCommand(tasks, command);
            } else if (formattedCommand.equals("list")) {
                printList(tasks);
            } else if (formattedCommand.startsWith("done")) {
                markAsDone(tasks, command);
            } else if (formattedCommand.startsWith("delete")) {
                removeTask(tasks, command);
            } else {
                DukeUI.printMessageWithBorders("Invalid command given. Please enter a new command.");
            }
            FileManager.writeToFile(tasks);
            command = in.nextLine();
            formattedCommand = command.toLowerCase();
        }
        DukeUI.showFarewellMessage();
    }

    private static void markAsDone(ArrayList<Task> tasks, String command) {
        int taskNum = -1;
        boolean proceedOnwards = false;
        try {
            taskNum = Integer.parseInt(command.substring(fiveChar));
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Please provide a valid number.");
        }

        if (proceedOnwards) {
            try {
                tasks.get(taskNum - 1).setDone();
                DukeUI.printMessageWithBorders("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks.get(taskNum - 1).getDescription());
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Number given is not in the list.");
            } catch (IndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Please provide a valid number.");
            }
        }
    }

    private static void printList(ArrayList<Task> tasks) {
        DukeUI.printHorizLine();
        System.out.println("Here are the tasks on your list: ");
        for (int i = 0; i < tasks.size() ; i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }
        DukeUI.printHorizLine();
    }

    private static void computeEventCommand(ArrayList<Task> tasks, String command) {
        String[] eventDetails = new String[2];
        try {
            extractCommandDetails(eventDetails, command, "Event");
            DukeUI.printAcknowledgement();
            tasks.add(new Event(eventDetails[0], eventDetails[1]));
        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Description for an Event cannot be left empty.");
        } finally {
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() + " tasks in the list.");
            DukeUI.printHorizLine();
        }
    }

    private static void computeDeadlineCommand(ArrayList<Task> tasks, String command) {
        String[] deadlineDetails = new String[2];
        try {
            extractCommandDetails(deadlineDetails, command, "Deadline");
            DukeUI.printAcknowledgement();
            tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));

        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Description for a Deadline cannot be left empty.");
        } finally {
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() +  " tasks in the list.");
            DukeUI.printHorizLine();
        }
    }

    private static void extractCommandDetails(String[] commandDetails, String command, String commandType)
            throws StringIndexOutOfBoundsException{
        int dividerPos = command.indexOf('/');
        commandDetails[0] = command.substring((commandType.equals("Deadline") ? nineChar : sixChar), dividerPos);
        commandDetails[1] = command.substring(dividerPos + 4);
    }

    private static void computeTodoCommand(ArrayList<Task> tasks, String command) {
        try {
            String description = command.substring(fiveChar);
            DukeUI.printAcknowledgement();
            tasks.add(new Todo(description));

        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Description for a Todo cannot be left empty.");
        } finally {
            System.out.println(tasks.get(tasks.size() - 1).toString());
            System.out.println("There are now " + tasks.size() + " tasks in the list.");
            DukeUI.printHorizLine();
        }
    }

    private static void removeTask(ArrayList<Task> tasks, String command) {
        int indexToDelete = -1;
        boolean proceedOnwards = false;
        try {
            indexToDelete = Integer.parseInt(command.substring(sevenChar)) - 1;
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Please provide a valid number.");
        }
        if (proceedOnwards) {
            try {
                String toDelete = tasks.get(indexToDelete).toString();
                tasks.remove(indexToDelete);
                System.out.println("Noted. I've removed this task");
                System.out.println(toDelete);
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Number given is not in the list.");
            } catch (IndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Please provide a valid number.");
            } finally {
                DukeUI.printHorizLine();
            }
        }
    }
}
