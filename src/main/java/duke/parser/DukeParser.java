package duke.parser;

import duke.tasks.Task;
import duke.ui.DukeUI;
import duke.parser.commands.*;
import duke.file.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeParser {
    /**
     * Parses the user-input and calls the appropriate function corresponding to the
     * command type. Stores all commands into a text file.
     *
     * @param tasks ArrayList of type Task storing all user-inputs
     */
    public static void executeUserCommand(ArrayList<Task> tasks) throws IOException {
        String command;
        String formattedCommand;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        formattedCommand = command.toLowerCase();

        while (!formattedCommand.equals("bye")) {
            if (formattedCommand.startsWith("todo")) {
                AddCommand.computeTodoCommand(tasks, command);
            } else if (formattedCommand.startsWith("deadline")) {
                AddCommand.computeDeadlineCommand(tasks, command);
            } else if (formattedCommand.startsWith("event")){
                AddCommand.computeEventCommand(tasks, command);
            } else if (formattedCommand.equals("list")) {
                ListCommand.printList(tasks);
            } else if (formattedCommand.startsWith("done")) {
                ListCommand.markAsDone(tasks, command);
            } else if (formattedCommand.startsWith("delete")) {
                DeleteCommand.removeTask(tasks, command);
            } else if (formattedCommand.startsWith("find")) {
                FindCommand.findKeyword(tasks, command);
            } else if (formattedCommand.startsWith("help")) {
                HelpCommand.showAvailableCommands();
            } else {
                DukeUI.printMessageWithBorders("Invalid command given. Please enter a new command.");
            }
            FileManager.writeToFile(tasks);
            command = in.nextLine();
            formattedCommand = command.toLowerCase();
        }
        DukeUI.showFarewellMessage();
    }
}
