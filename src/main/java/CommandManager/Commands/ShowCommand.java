package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;
import main.java.Model.StudyGroup;

public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит все элементы коллекции в строковом представлении.
     */
    public void execute(String argument) {
        if (collectionManager.getSize() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            for (StudyGroup studyGroup : collectionManager.getStudyGroups()) {
                System.out.println(studyGroup);
            }
        }
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}