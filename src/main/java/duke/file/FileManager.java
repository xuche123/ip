package duke.file;

import duke.tasks.*;
import duke.ui.DukeUI;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.util.Scanner;

public class FileManager {
    public static final String fileName = "duke.txt";

    public static void loadFile(ArrayList<Task> tasks) throws IOException {
        File dukeTxt = new File(fileName);
        if (dukeTxt.createNewFile()) {
            System.out.println("File does not exist. New file duke.txt created.");
        } else {
            System.out.println("File found and loaded.");
        }
        System.out.println(DukeUI.horizLine);
        Scanner scanner = new Scanner(dukeTxt);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            char taskType = taskData.charAt(0);
            boolean isDone = (taskData.charAt(4) == '1');
            String taskDetails = taskData.substring(8);
            String taskDescription = null;
            String taskTiming = null;

            if (taskType != 'T') {
                int dividerPos = taskDetails.indexOf('|');
                taskDescription = taskDetails.substring(0, dividerPos-1);
                taskTiming = taskDetails.substring(dividerPos + 2);
            }

            switch (taskType) {
            case 'T':
                tasks.add(new Todo(taskDetails));
                break;
            case 'D':
                tasks.add(new Deadline(taskDescription, taskTiming));
                break;
            case 'E':
                tasks.add(new Event(taskDescription, taskTiming));
                break;
            }
            if (isDone) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
    }

    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Task task:tasks) {
            fw.write(task.convertToFileString() + "\n");
        }
        fw.close();
    }
}
