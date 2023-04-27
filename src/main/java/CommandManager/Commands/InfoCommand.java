package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).
     */
    @Override
    public void execute(String argument) {
        String info = "Тип коллекции: " + collectionManager.getType() +
                "\nДата инициализации: " + (collectionManager.getLastInitTime() == null ? "Не инициализирована" : collectionManager.getLastInitTime()) +
                "\nКоличество элементов: " + collectionManager.getSize();
        System.out.println(info);
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}