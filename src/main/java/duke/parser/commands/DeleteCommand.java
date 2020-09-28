package duke.parser.commands;

import duke.tasks.Task;
import duke.ui.DukeUI;

import java.util.ArrayList;

public class DeleteCommand implements DukeCommands{
    public static void removeTask(ArrayList<Task> tasks, String command) {
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
                DukeUI.printMessageWithBorders("Noted. I've removed this task" + "\n" + toDelete);
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Number given is not in the list.");
            } catch (IndexOutOfBoundsException e) {
                DukeUI.printMessageWithBorders("Please provide a valid number.");
            }
        }
    }
}
