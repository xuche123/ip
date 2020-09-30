package duke.parser.commands;

import static duke.ui.DukeUI.horizLine;

public class HelpCommand implements DukeCommands{
    public static void showAvailableCommands() {
        System.out.println(horizLine);
        System.out.println("Here are the commands available:");
        System.out.println("1.todo");
        System.out.println("todo TASK_DESCRIPTION\n");
        System.out.println("2.deadline");
        System.out.println("deadline DEADLINE_DESCRIPTION /by WHEN\n");
        System.out.println("3.event");
        System.out.println("event EVENT_DESCRIPTION /at WHEN\n");
        System.out.println("4.list");
        System.out.println("list\n");
        System.out.println("5.done");
        System.out.println("done INDEX\n");
        System.out.println("6.delete");
        System.out.println("delete INDEX\n");
        System.out.println("7.find");
        System.out.println("find KEYWORD\n");
        System.out.println("8.bye");
        System.out.println("bye\n");
        System.out.println("9.help");
        System.out.println("help");
        System.out.println(horizLine);
    }
}
