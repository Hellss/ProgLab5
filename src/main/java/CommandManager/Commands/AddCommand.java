package main.java.CommandManager.Commands;

import main.java.Model.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand extends Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public AddCommand(CollectionManager collectionManager,Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    /**
     * Добавляет новый элемент в коллекцию.
     */

    @Override
    public void execute(String argument) {
        StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);
        StudyGroup studyGroup = studyGroupBuilder.build(collectionManager.generateNextId());
        if (studyGroup == null) {
            return;
        }
        collectionManager.add(studyGroup);
    }

    public void execute(String argument, String[] args) {
        StudyGroupBuilder studyGroupBuilder = new StudyGroupBuilder(scanner);
        StudyGroup studyGroup = studyGroupBuilder.build(collectionManager.generateNextId(), args);
        if (studyGroup == null) {
            System.out.println("Неверный ввод. Команда add не выполнена.");
            return;
        }
        collectionManager.add(studyGroup);
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию.";
    }

    @Override
    public String getFormat() {
        return "{element}";
    }
}