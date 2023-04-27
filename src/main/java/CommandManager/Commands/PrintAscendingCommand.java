package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

import java.util.List;
import main.java.Model.StudyGroup;

public class PrintAscendingCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        List<StudyGroup> studyGroups = collectionManager.getSortedStudyGroups();
        for (StudyGroup studyGroup : studyGroups) {
            System.out.println(studyGroup);
        }
    }

    @Override
    public String getDescription() {
        return "вывести элементы коллекции в порядке возрастания";
    }
}
