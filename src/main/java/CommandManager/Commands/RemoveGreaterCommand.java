package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;
import main.java.Model.StudyGroup;
import main.java.Model.StudyGroupBuilder;

import java.util.Scanner;

public class RemoveGreaterCommand extends Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public RemoveGreaterCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }
    /**
     * Выполнение команды.
     * Удаляет все элементы коллекции, которые больше заданного.
     *
     * @param argument аргумент команды
     */
    @Override
    public void execute(String argument) {
        StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);

        StudyGroup studyGroup = studyGroupBuilder.build(collectionManager.generateNextId());
        if (studyGroup == null)
            return;
        int count = collectionManager.removeGreater(studyGroup);
        if (count > 0)
            System.out.println("Все элементы коллекции, которые больше заданного, удалены. ("+ count + " элементов)");
        else
            System.out.println("Не нашлось таких элементов для удаления");
    }

    public void execute(String argument, String[] args) {
        StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);

        StudyGroup studyGroup = studyGroupBuilder.build(collectionManager.generateNextId(), args);
        if (studyGroup == null) {
            System.out.println("Неверный ввод. Команда remove_greater не выполнена.");
            return;
        }
        int count = collectionManager.removeGreater(studyGroup);
        if (count > 0)
            System.out.println("Все элементы коллекции, которые больше заданного, удалены. ("+ count + " элементов)");
        else
            System.out.println("Не нашлось таких элементов для удаления");
    }

    /**
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, превышающие заданный.";
    }

    @Override
    public String getFormat() {
        return "{element}";
    }

}
