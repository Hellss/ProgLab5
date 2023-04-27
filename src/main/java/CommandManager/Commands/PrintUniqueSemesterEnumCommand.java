package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;
import main.java.Model.Semester;

import java.util.List;

public class PrintUniqueSemesterEnumCommand extends Command {
    private final CollectionManager collectionManager;
    public PrintUniqueSemesterEnumCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Выводит уникальные значения SemesterEnum всех элементов коллекции в порядке возрастания.
     */
    @Override
    public void execute(String argument) {
        List<Semester> semesterEnums = collectionManager.getUniqueSemesterEnums();
        if (semesterEnums.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Уникальные значения SemesterEnum всех элементов коллекции:");
            for (Semester semesterEnum : semesterEnums) {
                System.out.println("- " + semesterEnum);
            }
        }
    }

    @Override
    public String getDescription() {
        return "вывести уникальные значения поля semesterEnum всех элементов коллекции";
    }
}
