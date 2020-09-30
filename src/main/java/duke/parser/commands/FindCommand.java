package duke.parser.commands;

import duke.tasks.Task;
import duke.ui.DukeUI;

import java.util.ArrayList;
/**
 * Handles user commands of type <code>find</code>.
 */
public class FindCommand implements DukeCommands{
    /**
     * Handles user-inputted command of type Find. Shows an error message when the
     * command does not specify any keyword.
     *
     * @param tasks ArrayList of type Task.
     * @param command User-inputted command
     *
     */
    public static void findKeyword(ArrayList<Task> tasks, String command) {
        String keyword = null;
        boolean proceedOnwards = false;
        ArrayList<Task> matchedTasks = new ArrayList<>();
        try {
            keyword = command.substring(fiveChar);
            proceedOnwards = true;
        } catch (StringIndexOutOfBoundsException e) {
            DukeUI.printMessageWithBorders("Find command must be used with a keyword/phrase.");
        }

        if (proceedOnwards) {
            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
            printMatchingList(matchedTasks);
        }
    }

    public static void printMatchingList(ArrayList<Task> tasks) {
        DukeUI.printHorizLine();
        System.out.println("Here are the matching tasks on your list: ");
        for (int i = 0; i < tasks.size() ; i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }
        DukeUI.printHorizLine();
    }

}
