package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

import java.util.Scanner;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public RemoveByIdCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    /**
     * Удаляет элемент из коллекции по его id.
     */
    @Override
    public void execute(String argument) {
        try {
            int id = Integer.parseInt(argument.trim());
            if (collectionManager.removeById(id)) {
                System.out.println("Элемент с id " + id + " успешно удален.");
            } else {
                System.out.println("Элемент с id " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id. Пожалуйста, введите целое число.");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент из коллекции по его id";
    }

    public String getFormat() {return "id"; }
}