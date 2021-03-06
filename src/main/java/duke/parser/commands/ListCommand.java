package duke.parser.commands;

import duke.tasks.Task;
import duke.ui.DukeUI;

import java.util.ArrayList;

/**
 * Handles user commands of type <code>list</code> and type <code>done</code>.
 */

public class ListCommand implements DukeCommands {
    /**
     * Mark a specific task as done. If task entered does not exist an error message
     * will be printed.
     *
     * @param tasks ArrayList of type Task.
     * @param command User-input command in the form of 'done X' where X is an integer
     *
     */
    public static void markAsDone(ArrayList<Task> tasks, String command) {
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

    public static void printList(ArrayList<Task> tasks) {
        DukeUI.printHorizLine();
        System.out.println("Here are the tasks on your list: ");
        for (int i = 0; i < tasks.size() ; i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }
        DukeUI.printHorizLine();
    }
}
