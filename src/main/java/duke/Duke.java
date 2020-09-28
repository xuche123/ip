package duke;

import duke.tasks.Task;
import duke.ui.DukeUI;
import duke.parser.DukeParser;
import duke.file.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        dukeRun();
    }

    private static void dukeRun() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        DukeUI.showWelcomeMessage();
        FileManager.loadFile(tasks);
        DukeParser.executeUserCommand(tasks);
    }
}