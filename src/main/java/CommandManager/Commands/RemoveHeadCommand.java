package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

public class RemoveHeadCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет первый элемент коллекции.
     */
    @Override
    public void execute(String argument) {
        collectionManager.removeHead();
    }

    @Override
    public String getDescription() {
        return "Удалить первый элемент коллекции.";
    }
}