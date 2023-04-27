package main.java.CommandManager.Commands;

import main.java.Model.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCommand extends Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public UpdateCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    /**
     * Обновляет данные элемента коллекции по его id.
     */

    @Override
    public void execute(String argument) {
        try {
            int id = Integer.parseInt(argument);
            StudyGroup prevStudyGroup = collectionManager.getById(id);
            if (prevStudyGroup == null) {
                System.out.println("Элемента с указанным id нет в коллекции.");
                return;
            }

            StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);
            StudyGroup studyGroup = studyGroupBuilder.build(id);
            if (studyGroup == null) {
                return;
            }
            collectionManager.removeById(id);
            collectionManager.add(studyGroup);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод id");
        }
    }

    @Override
    public void execute(String argument, String args[]) {
        try {
            int id = Integer.parseInt(argument);
            StudyGroup prevStudyGroup = collectionManager.getById(id);
            if (prevStudyGroup == null) {
                System.out.println("Элемента с указанным id нет в коллекции.");
                return;
            }

            StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);
            StudyGroup studyGroup = studyGroupBuilder.build(id, args);
            if (studyGroup == null) {
                System.out.println("Неверный ввод. Команда update не выполнена.");
                return;
            }
            collectionManager.removeById(id);
            collectionManager.add(studyGroup);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод id");
        }
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному.";
    }

    @Override
    public String getFormat() {
        return "id";
    }
}