package duke.parser.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.DukeUI;

import java.util.ArrayList;

/**
 * Handles user commands of type <code>deadline</code>, <code>to do</code>
 * and type <code>event</code>.
 */
public class AddCommand implements DukeCommands{
    /**
     * Handles user-inputted command of type Event. Shows an error message when description
     * of command is left empty.
     *
     * @param tasks ArrayList of type Task.
     * @param command User-inputted command
     *
     */
    public static void computeEventCommand(ArrayList<Task> tasks, String command) {
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

    /**
     * Handles user-inputted command of type Deadline. Shows an error message when description
     * of command is left empty.
     *
     * @param tasks ArrayList of type Task.
     * @param command User-inputted command
     *
     */
    public static void computeDeadlineCommand(ArrayList<Task> tasks, String command) {
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

    /**
     * Extract description and timings from user-inputted command of type Event or Deadline
     *
     * @param commandDetails Array storing the description in the index 0 and timing in index 1.
     * @param command User-inputted command
     * @param commandType Either deadline or event
     *
     * @throws StringIndexOutOfBoundsException if String command is NULL.
     */
    private static void extractCommandDetails(String[] commandDetails, String command, String commandType)
            throws StringIndexOutOfBoundsException{
        int dividerPos = command.indexOf('/');
        commandDetails[0] = command.substring((commandType.equals("Deadline") ? nineChar : sixChar), dividerPos);
        commandDetails[1] = command.substring(dividerPos + 4);
    }

    /**
     * Handles user-inputted command of type to do.
     *
     * @param tasks ArrayList of type Task.
     * @param command User-inputted command
     *
     */
    public static void computeTodoCommand(ArrayList<Task> tasks, String command) {
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
}
