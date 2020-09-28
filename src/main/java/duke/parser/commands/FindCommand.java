package duke.parser.commands;

import duke.tasks.Task;
import duke.ui.DukeUI;

import java.util.ArrayList;
public class FindCommand implements DukeCommands{
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
