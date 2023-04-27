package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

public class ClearCommand extends Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию.
     */

    @Override
    public void execute(String argument) {
        collectionManager.clear();
        System.out.println("Коллекция очищена.");
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию.";
    }
}